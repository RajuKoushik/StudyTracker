package com.example.studyapp.studytracker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.text.Editable;

import java.util.List;

@Entity(tableName = "subject")
public class Subject {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "subject_name")
    private String subjectName;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        if (uid != subject.uid) return false;
        return subjectName != null ? subjectName.equals(subject.subjectName) : subject.subjectName == null;
    }



    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "uid=" + uid +
                ", subject='" + subjectName + '\'' +
                '}';
    }


}


