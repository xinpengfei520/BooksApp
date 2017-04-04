package com.xpf.booksapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xpf.booksapp.R;
import com.xpf.booksapp.model.LibsInfo;

// 添加图书页面
public class AddBookActivity extends Activity implements View.OnClickListener {

    private EditText etLibName, etTypeId, etLib_id;
    private Button btnAdd;
    private Intent intent;
    private int str_type_id, str_lib_id;
    private String str_lib_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etLibName = (EditText) findViewById(R.id.lib_name);
        etTypeId = (EditText) findViewById(R.id.type_id);
        btnAdd = (Button) findViewById(R.id.btn_add_book);
        etLib_id = (EditText) findViewById(R.id.lib_id);

        btnAdd.setOnClickListener(this);

        intent = getIntent();  // 获取传递过来的intent
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_book:
                addBook();
                finish();
                break;
        }

    }

    private void addBook() {
        // 获取输入框的值
        str_lib_name = etLibName.getText().toString().trim(); // 取消后面空格
        str_lib_id = etLib_id.getId();
        str_type_id = etTypeId.getId();

        LibsInfo libsInfo = new LibsInfo();
        libsInfo.setLib_name(str_lib_name);
        libsInfo.setId(str_lib_id);
        libsInfo.setType_id(str_type_id);

        boolean ifSaveSuccess = libsInfo.save();
        if (ifSaveSuccess) {
            Toast.makeText(AddBookActivity.this, "存储成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddBookActivity.this, "存储失败！", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK, intent);
    }

}


