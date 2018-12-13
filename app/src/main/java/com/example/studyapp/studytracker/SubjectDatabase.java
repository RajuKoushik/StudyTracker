package com.example.studyapp.studytracker;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.SyncStateContract;


@Database(entities = { Subject.class }, version = 1)
public abstract class SubjectDatabase extends RoomDatabase {

    public abstract SubjectDao getSubjectDao();

    private static SubjectDatabase subjectDb;

    public static SubjectDatabase getInstance(Context context) {
        if (null == subjectDb) {
            subjectDb = buildDatabaseInstance(context);
        }
        return subjectDb;
    }

    private static SubjectDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                SubjectDatabase.class,
                "subject-database")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        subjectDb = null;
    }

}
