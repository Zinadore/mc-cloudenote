package iboutsikas.practical.org.cloudenoteapplication.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import iboutsikas.practical.org.cloudenoteapplication.InjectableApplication;
import iboutsikas.practical.org.cloudenoteapplication.R;
import iboutsikas.practical.org.cloudenoteapplication.contracts.IRepository;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Zinadore on 6/3/2017.
 */

public class NoteDetailsActivity extends AppCompatActivity {
    @Inject IRepository<Note> mNoteRepository;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        ((InjectableApplication)getApplication()).AppComponent().inject(this);

        int index = getIntent().getIntExtra("note_index", 0);

        Note note = this.mNoteRepository.Get(index);

        if(note == null){
            Toast.makeText(this, R.string.msg_note_created, Toast.LENGTH_SHORT).show();
            return;
        }


        final Button deleteButton = (Button) findViewById(R.id.btn_delete_note);
        final TextView dateText = (TextView) findViewById(R.id.tv_notedetails_date);
        final TextView contentText = (TextView) findViewById(R.id.tv_notedetails_content);

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        String formatedDate = dt.format(note.Date());
        dateText.setText(formatedDate);

        contentText.setText(note.Content());
        Context self = this;

        deleteButton.setOnClickListener(v -> {
            this.mNoteRepository.Delete(self, note.ID());
        });
    }
}
