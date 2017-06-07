package iboutsikas.practical.org.cloudenoteapplication.repositories;

import android.content.Context;
import android.widget.Toast;
import iboutsikas.practical.org.cloudenoteapplication.R;
import iboutsikas.practical.org.cloudenoteapplication.contracts.INotesApi;
import iboutsikas.practical.org.cloudenoteapplication.contracts.IRepository;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Zinadore on 6/2/2017.
 */

public class NoteRepository implements IRepository<Note> {

    private static class NoteComparator implements Comparator<Note> {

        @Override
        public int compare(Note o1, Note o2) {
            return o2.Date().compareTo(o1.Date());
        }
    }

    private List<Note> notes;
    private INotesApi mNotesApi;

    private BehaviorSubject<List<Note>> mItemsSubject;

    @Inject
    public NoteRepository(INotesApi notesApi) {
//        ArrayList<Note> temp = new ArrayList<Note>();
//        Note third = new Note(3, "This is the third note", new Date());
//        try {
//
//
//            String start = "02/08/1992";
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            Date oldDate = formatter.parse(start); //the result
//
//            temp.add(new Note(1, "This is the first note", oldDate));
//        } catch (Exception e) {
//
//        }
//        temp.add(new Note(2, "This is the second note", new Date()));
//
//        temp.add(third);
//
//        this.notes = new ArrayList<>(temp);
//        Collections.sort(this.notes, new NoteComparator());
        this.notes = new ArrayList<>();
        this.mItemsSubject = BehaviorSubject.createDefault(Collections.unmodifiableList(this.notes));
        this.mNotesApi = notesApi;
    }

    @Override
    public Observable<List<Note>> Items() {
        return this.mItemsSubject;
    }
    @Override
    public List<Note> GetAll() {
        return this.mItemsSubject.getValue();
    }

    @Override
    public Note Get(int index) {
        if(index >= 0 && index <= this.notes.size()) {
            return this.notes.get(index);
        }
        return null;
    }

    @Override
    public void FetchAll(Context context) {
        NoteRepository self = this;

        this.mNotesApi.getAll().enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                self.notesReceived(response.body());
                Toast.makeText(context, R.string.msg_notes_received, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Toast.makeText(context, R.string.msg_notes_received_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void Create(Context context, Note newEntity) {

        this.mNotesApi.create(newEntity).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context, R.string.msg_note_created, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, R.string.msg_note_error, Toast.LENGTH_SHORT).show();
            }
        });
        this.notes.add(newEntity);
        this.mItemsSubject.onNext(Collections.unmodifiableList(this.notes));
    }

    @Override
    public void Delete(Context context, int id) {
        this.mNotesApi.delete(id).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context, R.string.msg_note_deleted, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, R.string.msg_note_deleted_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notesReceived(List<Note> notes) {
        this.notes = new ArrayList<>(notes);
        Collections.sort(this.notes, new NoteComparator());
        this.mItemsSubject.onNext(Collections.unmodifiableList(this.notes));
    }
}
