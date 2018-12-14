package com.example.studyapp.studytracker;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WhatStudyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private SubjectDatabase subjectDatabase;
    private List<Subject> subjects;
    private SubjectAdapter subjectAdapter;

    static List<Subject> subjectsy;

    private Spinner spinner;
    private static  String[] paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_study);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayList();




        spinner = (Spinner)findViewById(R.id.spinner_drop);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(WhatStudyActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void displayList(){
// initialize database instance
        subjectDatabase = SubjectDatabase.getInstance(WhatStudyActivity.this);
// fetch list of notes in background thread
        new WhatStudyActivity.RetrieveTask(this).execute();
    }






    private static class RetrieveTask extends AsyncTask<Void,Void,List<Subject>> {

        private WeakReference<WhatStudyActivity> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(WhatStudyActivity context) {
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




               subjectsy = subjects;

                for(int i =0;i < subjectsy.size();i++)
                {
                    paths[i] = subjectsy.get(i).getSubjectName();
                }


            }
        }

    }






}
