package com.example.studyapp.studytracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

public class SubjectListActivity extends AppCompatActivity implements SubjectAdapter.OnNoteItemClick  {

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private SubjectDatabase subjectDatabase;
    private List<Subject> subjects;
    private SubjectAdapter subjectAdapter;

    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initializeVies();
        displayList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void displayList(){
// initialize database instance
        subjectDatabase = SubjectDatabase.getInstance(SubjectListActivity.this);
// fetch list of notes in background thread
        new RetrieveTask(this).execute();
    }

    @Override
    public void onNoteClick(int pos) {

        Toast.makeText(SubjectListActivity.this, pos+" Position",
                Toast.LENGTH_LONG).show();

    }

    private static class RetrieveTask extends AsyncTask<Void,Void,List<Subject>> {

        private WeakReference<SubjectListActivity> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(SubjectListActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Subject> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().subjectDatabase.getSubjectDao().getAll();



            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Subject> subjects) {
            if (subjects!=null && subjects.size()>0 ){
                activityReference.get().subjects = subjects;

                // hides empty text view
//                activityReference.get().textViewMsg.setVisibility(View.GONE);

                // create and set the adapter on RecyclerView instance to display list
                activityReference.get().subjectAdapter = new SubjectAdapter(subjects,activityReference.get());
                activityReference.get().recyclerView.setAdapter(activityReference.get().subjectAdapter);
            }
        }

    }

    private void initializeVies(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubjectListActivity.this));

    }


}





