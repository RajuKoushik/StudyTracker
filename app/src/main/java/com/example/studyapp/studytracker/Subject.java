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

    @ColumnInfo(name = "subject_location")
    private String subjectLocation;

    @ColumnInfo(name = "subject_hours")
    private String subjectHours;

    @ColumnInfo(name = "subject_topic")
    private String subjectTopic;

    @ColumnInfo(name = "subject_date")
    private String subjectDate;


    public Subject(String subjectName,  String subjectLocation,String subjectHours, String subjectTopic, String subjectDate) {
        this.subjectName = subjectName;
        this.subjectHours = subjectHours;
        this.subjectLocation = subjectLocation;
        this.subjectTopic = subjectTopic;
        this.subjectDate = subjectDate;
    }


    public String getSubjectDate() {
        return subjectDate;
    }

    public String getSubjectTopic() {
        return subjectTopic;
    }

    public void setSubjectDate(String subjectDate) {
        this.subjectDate = subjectDate;
    }

    public void setSubjectTopic(String subjectTopic) {
        this.subjectTopic = subjectTopic;
    }

    public String getSubjectHours() {
        return subjectHours;
    }

    public String getSubjectLocation() {
        return subjectLocation;
    }

    public void setSubjectHours(String subjectHours) {
        this.subjectHours = subjectHours;
    }

    public void setSubjectLocation(String subjectLocation) {
        this.subjectLocation = subjectLocation;
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
                ", subject_location='" + subjectLocation + '\'' +
                ", subject_hours='" + subjectHours + '\'' +
                ", subject_topic='" + subjectTopic + '\'' +
                ", subject_date='" + subjectTopic + '\'' +


                '}';
    }


}


