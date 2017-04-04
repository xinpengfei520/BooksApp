package com.xpf.booksapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xpf.booksapp.R;
import com.xpf.booksapp.adapter.MyAdapter;
import com.xpf.booksapp.model.LibsInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

// 我的订阅页面
public class MyBookActivity extends Activity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private MyAdapter Adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<LibsInfo> libsInFoList;
    private Button btnAdd;
    private Context mContext;
    private List<LibsInfo> bookList;
    private List<List<String>> mList = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_my_book);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_book_recycler_view);
        // 以下，如果可以确定每个Item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        // 以下，创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        getAllBooks(); // 获取所有图书
    }

    /**
     * 获取全部图书信息
     */
    private void getAllBooks() {
//        libsInFoList = DataSupport.select("lib_name").find(LibsInfo.class);
        //Toast.makeText(MyBookActivity.this, libsInFoList.get(0).getId()+"",0).show();

        List<LibsInfo> all = DataSupport.findAll(LibsInfo.class);
        if (all != null && all.size() > 0) {
            bookList = all;
            populateDataFromDB();
        } else {
            Toast.makeText(MyBookActivity.this, "没有查找到符合条件的数据！", Toast.LENGTH_SHORT).show();
        }

        /**
         * 判空
         */
        if (libsInFoList != null && libsInFoList.size() > 0) {
            // 以下，创建并设置Adapter
            Adapter = new MyAdapter(MyBookActivity.this, libsInFoList);
            mRecyclerView.setAdapter(Adapter);
        }
    }

    private void populateDataFromDB() {
        mList.clear();
        List<String> columnList = new ArrayList<String>();
        columnList.add("id");
        columnList.add("name");
        columnList.add("type");
        mList.add(columnList);

        if (bookList != null && bookList.size() > 0) {
            for (int i = 0; i < bookList.size(); i++) {
                LibsInfo user = bookList.get(i);
                List<String> stringList = new ArrayList<String>();
                stringList.add(String.valueOf(user.getId()));
                stringList.add(user.getId() + "");
                stringList.add(user.getLib_name());
                stringList.add(user.getType_id() + "");
                mList.add(stringList);
            }
            Adapter.notifyDataSetChanged();
            bookList.clear();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 根据返回的resultCode判断是通过哪种操作返回的，并提示相关信息；
        switch (requestCode) {
            case 0:
                if (resultCode == 1)
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                if (resultCode == 2)
                    Toast.makeText(this, "已删除", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
        }
        /**
         * 如果这里仅仅使用adapter.notifyDataSetChanged()是不会刷新界面ListView的，
         * 因为此时adapter中传入的studentList并没有给刷新，即adapter也没有被刷新，所以你可以
         * 重新获取studentList后再改变adapter，我这里通过调用onCreate()重新刷新了整个界面
         */
        getAllBooks(); // 获取所有图书
        /**
         * 先判空再设置适配器，防止空指针异常
         */
        if (libsInFoList != null && libsInFoList.size() > 0) {
            Adapter = new MyAdapter(mContext, libsInFoList);
            mRecyclerView.setAdapter(Adapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: // 添加图书
                Intent i = new Intent(MyBookActivity.this, AddBookActivity.class);
                startActivityForResult(i, 1);
                break;
        }
    }
}
