package com.xpf.booksapp.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 00687560 on 2017/3/27.
 * 学生bean类
 */

public class StudentInfo extends DataSupport {
    private int id;
    private int password;
    private int type_id;
    private int collect_id;
    private int book_id;
    private int search_id;

    private List<LibsType> LibsTypeList = new ArrayList<LibsType>();
    private List<LibsInfo> LibsInfotList = new ArrayList<LibsInfo>();
    private List<SearchHistory> SearchHistoryList = new ArrayList<SearchHistory>();

    public StudentInfo() {
    }

    public StudentInfo(int id, int password) {
        this.id = id;
        this.password = password;
    }

    public StudentInfo(int id, int password, int type_id, int collect_id,
                       int book_id, int search_id, List<LibsType> libsTypeList,
                       List<LibsInfo> libsInfotList, List<SearchHistory> searchHistoryList) {
        this.id = id;
        this.password = password;
        this.type_id = type_id;
        this.collect_id = collect_id;
        this.book_id = book_id;
        this.search_id = search_id;
        LibsTypeList = libsTypeList;
        LibsInfotList = libsInfotList;
        SearchHistoryList = searchHistoryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(int collect_id) {
        this.collect_id = collect_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getSearch_id() {
        return search_id;
    }

    public void setSearch_id(int search_id) {
        this.search_id = search_id;
    }

    public List<LibsType> getLibsTypeList() {
        return LibsTypeList;
    }

    public void setLibsTypeList(List<LibsType> libsTypeList) {
        LibsTypeList = libsTypeList;
    }

    public List<LibsInfo> getLibsInfotList() {
        return LibsInfotList;
    }

    public void setLibsInfotList(List<LibsInfo> libsInfotList) {
        LibsInfotList = libsInfotList;
    }

    public List<SearchHistory> getSearchHistoryList() {
        return SearchHistoryList;
    }

    public void setSearchHistoryList(List<SearchHistory> searchHistoryList) {
        SearchHistoryList = searchHistoryList;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", password=" + password +
                ", type_id=" + type_id +
                ", collect_id=" + collect_id +
                ", book_id=" + book_id +
                ", search_id=" + search_id +
                ", LibsTypeList=" + LibsTypeList +
                ", LibsInfotList=" + LibsInfotList +
                ", SearchHistoryList=" + SearchHistoryList +
                '}';
    }
}
