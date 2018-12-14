package com.example.studyapp.studytracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;

public class AddSubject extends AppCompatActivity {

    private EditText et_subject;
    private EditText et_location;
    private EditText et_hours;
    private EditText et_topic;
    private EditText et_date;


    private SubjectDatabase subjectDatabase;
    private Subject subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_subject = (EditText)findViewById(R.id.subject_name);
        et_location = (EditText)findViewById(R.id.subject_location);
        et_hours = (EditText)findViewById(R.id.subject_hours);
        et_topic = (EditText)findViewById(R.id.subject_topic);
        et_date = (EditText)findViewById(R.id.subject_date);
        subjectDatabase = SubjectDatabase.getInstance(AddSubject.this);
        Button button = findViewById(R.id.but_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fetch data and create note object
                subject = new Subject(et_subject.getText().toString(),et_location.getText().toString(),et_hours.getText().toString(),et_topic.getText().toString(),et_date.getText().toString());



                // create worker thread to insert data into database
                new InsertTask(AddSubject.this,subject).execute();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void setResult(Subject subject, int flag){
        setResult(flag,new Intent().putExtra("subject", String.valueOf(subject)));
        finish();
    }


    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<AddSubject> activityReference;
        private Subject subject;

        // only retain a weak reference to the activity
        InsertTask(AddSubject context, Subject subject) {
            activityReference = new WeakReference<>(context);
            this.subject = subject;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().subjectDatabase.getSubjectDao().insertAll(subject);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                activityReference.get().setResult(subject,1);
            }
        }

    }

}
