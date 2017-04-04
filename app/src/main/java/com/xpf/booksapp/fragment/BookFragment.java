package com.xpf.booksapp.fragment;

import android.util.Log;
import android.view.View;

import com.xpf.booksapp.R;
import com.xpf.booksapp.base.BaseFragment;

/**
 * Created by xpf on 2017/3/22 :)
 * Function: 图书页面的Fragment
 */

public class BookFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_book, null);
        Log.e("TAG", " 图书页面的视图初始化了");
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", " 图书页面的数据初始化了");
    }

}
