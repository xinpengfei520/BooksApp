package com.xpf.booksapp;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by xpf on 2017/3/30 :)
 * Function:
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化LitePal数据库
        LitePal.initialize(this);
    }
}
