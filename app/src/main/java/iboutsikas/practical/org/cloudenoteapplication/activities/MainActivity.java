package iboutsikas.practical.org.cloudenoteapplication.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import iboutsikas.practical.org.cloudenoteapplication.InjectableApplication;
import iboutsikas.practical.org.cloudenoteapplication.NoteListAdapter;
import iboutsikas.practical.org.cloudenoteapplication.R;
import iboutsikas.practical.org.cloudenoteapplication.contracts.IRepository;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject IRepository<Note> mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((InjectableApplication)getApplication()).AppComponent().inject(this);

        this.mNoteRepository.FetchAll(this);

        final ListView notesList = (ListView) findViewById(R.id.lv_notes);
        final NoteListAdapter adapter = new NoteListAdapter(this, mNoteRepository.Items());

        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra("note_index", position);
            startActivity(intent);
        });

        final FloatingActionButton newNoteButton = (FloatingActionButton) findViewById(R.id.btn_new_note);

        newNoteButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
        });

    }
}
