package iboutsikas.practical.org.cloudenoteapplication.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import iboutsikas.practical.org.cloudenoteapplication.utils.CustomDatePicker;
import iboutsikas.practical.org.cloudenoteapplication.InjectableApplication;
import iboutsikas.practical.org.cloudenoteapplication.R;
import iboutsikas.practical.org.cloudenoteapplication.contracts.IRepository;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;

import javax.inject.Inject;

/**
 * Created by Zinadore on 6/3/2017.
 */

public class NewNoteActivity extends AppCompatActivity {

    @Inject IRepository<Note> mNoteRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        ((InjectableApplication)getApplication()).AppComponent().inject(this);

        final CustomDatePicker datePicker = new CustomDatePicker(this, R.id.et_newnote_date);
        final Button saveButton = (Button) findViewById(R.id.btn_save_note);
        final EditText contentText = (EditText) findViewById(R.id.et_newnote_content);
        Context self = this;
        saveButton.setOnClickListener(v -> {
            Note tempNote = new Note(contentText.getText().toString(), datePicker.getSelectedDate());
            this.mNoteRepository.Create(self, tempNote);
        });

    }
}
