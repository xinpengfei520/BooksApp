package com.xpf.booksapp.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 00687560 on 2017/3/27.
 */

public class LibsType extends DataSupport {

    private int id;
    private String type_name;
    private List<StudentInfo> StudentInfotList = new ArrayList<StudentInfo>();
    private List<LibsInfo> LibsInfoList = new ArrayList<LibsInfo>();

    public LibsType() {
    }

    public LibsType(int id, String type_name, List<StudentInfo> studentInfotList, List<LibsInfo> libsInfoList) {
        this.id = id;
        this.type_name = type_name;
        StudentInfotList = studentInfotList;
        LibsInfoList = libsInfoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<StudentInfo> getStudentInfotList() {
        return StudentInfotList;
    }

    public void setStudentInfotList(List<StudentInfo> studentInfotList) {
        StudentInfotList = studentInfotList;
    }

    public List<LibsInfo> getLibsInfoList() {
        return LibsInfoList;
    }

    public void setLibsInfoList(List<LibsInfo> libsInfoList) {
        LibsInfoList = libsInfoList;
    }

    @Override
    public String toString() {
        return "LibsType{" +
                "id=" + id +
                ", type_name=" + type_name +
                ", StudentInfotList=" + StudentInfotList +
                ", LibsInfoList=" + LibsInfoList +
                '}';
    }
}
