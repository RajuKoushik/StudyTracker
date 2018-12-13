package com.example.studyapp.studytracker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

@Dao
public interface SubjectDao {

    @Query("SELECT * FROM subject")
    List<Subject> getAll();

    @Query("SELECT * FROM subject where subject_name LIKE  :subjectName")
    Subject findBySubjectName(String subjectName);

    @Query("SELECT COUNT(*) from subject")
    int countSubjects();

    @Insert
    void insertAll(Subject... subjects);

    @Delete
    void delete(Subject subject);
}




