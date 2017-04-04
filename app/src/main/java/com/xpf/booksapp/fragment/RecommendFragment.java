package com.xpf.booksapp.fragment;

import android.util.Log;
import android.view.View;

import com.xpf.booksapp.R;
import com.xpf.booksapp.base.BaseFragment;

/**
 * Created by xpf on 2017/3/22 :)
 * Function: 为我推荐页面的Fragment
 */

public class RecommendFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        Log.e("TAG", " 为我推荐页面的视图初始化了");
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", " 为我推荐页面的数据初始化了");
    }

}
