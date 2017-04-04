package com.xpf.booksapp.model;

import org.litepal.crud.DataSupport;

/**
 * Created by 00687560 on 2017/3/27.
 */

public class SearchHistory extends DataSupport {
    private int id;
    private int user_id;
    private String search_name;
    private StudentInfo studentInfo;

    public SearchHistory() {
    }

    public SearchHistory(int id, int user_id, String search_name, StudentInfo studentInfo) {
        this.id = id;
        this.user_id = user_id;
        this.search_name = search_name;
        this.studentInfo = studentInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", search_name='" + search_name + '\'' +
                ", studentInfo=" + studentInfo +
                '}';
    }
}
