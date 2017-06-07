package iboutsikas.practical.org.cloudenoteapplication;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;
import io.reactivex.Observable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Zinadore on 6/3/2017.
 */

public class NoteListAdapter extends ReactiveListAdapter<Note> {

    public NoteListAdapter(Context context, Observable<List<Note>> source) {
        super(context, source);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.notes_list_row, parent, false);
        }
        Note note = this.getItem(position);

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        String formatedDate = dt.format(note.Date());
        TextView dateText = (TextView) convertView.findViewById(R.id.tv_notelist_date);
        dateText.setText(formatedDate);

        TextView noteText = (TextView) convertView.findViewById(R.id.tv_notelist_note);
        noteText.setEllipsize(TextUtils.TruncateAt.END);
        noteText.setText(note.Content());
        return convertView;
    }

}
