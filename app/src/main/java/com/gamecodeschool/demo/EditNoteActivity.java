package com.gamecodeschool.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gamecodeschool.demo.database.DatabaseHelper;
import com.gamecodeschool.demo.database.Note;

public class EditNoteActivity extends AppCompatActivity {
    int noteId;
    EditText etTitle;
    EditText etNote;
    Button btnAddPhoto;
    Button btnAddVoiceNote;
    Button btnUpdate;
    String title;
    String noteText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etTitle=findViewById(R.id.etTitle);
        etNote=findViewById(R.id.etNote);
        btnAddPhoto=findViewById(R.id.btnAddPhoto);
        btnAddVoiceNote=findViewById(R.id.btnAddVoiceNote);
        btnUpdate = findViewById(R.id.btnUpdate);

        getNoteId();


        displayNote();




                Toast.makeText(getBaseContext(),"You have clicked the save button",Toast.LENGTH_LONG).show();
            }

    private void getNoteId() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteId = extras.getInt("NOTE_ID");

        }
    }

    private void displayNote() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
        Note note = databaseHelper.getNoteById(noteId);
        etTitle.setText(note.getTitle());
        etNote.setText(note.getNoteText());

}

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Update",btnUpdate.getText().toString());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=etTitle.getText().toString();
                noteText=etNote.getText().toString();
                Note note = new Note(noteId,title,noteText);
                DatabaseHelper databaseHelper= new DatabaseHelper(getBaseContext(),"notes",null,1);
                databaseHelper.updateNote(note);
                finish();
            }
        });

    }
}

