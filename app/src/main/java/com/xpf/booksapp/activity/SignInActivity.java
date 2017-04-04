package com.xpf.booksapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xpf.booksapp.R;
import com.xpf.booksapp.model.StudentInfo;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

// 登录页面
public class SignInActivity extends Activity {

    private Button btnSignIn;
    private EditText user_id, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // 删除navigationBar
        getWindow().
                getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        user_id = (EditText) findViewById(R.id.user_id);
        user_password = (EditText) findViewById(R.id.user_password);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIdAndPwd();
            }
        });
        /**
         * 点击 忘记密码 文本跳转
         */
        //借助SpannableString类实现超链接文字
        TextView tv_link = (TextView) findViewById(R.id.tv_link);
        tv_link.setText(getClickable());
        //设置超链接可点击
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ForgotPassword1Activity.class));
            }
        });

        SQLiteDatabase db = Connector.getDatabase(); // 创建数据库
        saveUsers();
    }

    /**
     * 事先存储一些用户名和密码到数据库
     */
    private void saveUsers() {
        for (int i = 0; i < 10; i++) {
            StudentInfo studentInfo = new StudentInfo(i, 123456);
            studentInfo.save();
        }
    }

    /**
     * 检查用户名和密码
     */
    private void checkIdAndPwd() {
        String id = user_id.getText().toString().trim();
        String pwd = user_password.getText().toString().trim();
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(SignInActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

        } else { // 判断用户名和密码是否正确
            StudentInfo studentInfo = DataSupport.find(StudentInfo.class, Integer.parseInt(id));
            if (studentInfo != null) {
                int password = studentInfo.getPassword();
                Log.e("TAG", "password===" + password);
                if (("" + password).equals(pwd)) {
                    startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                    finish(); // 登录成功后销毁登录页面！！！
                } else {
                    Toast.makeText(SignInActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignInActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * 定义getClickable方法可点击
     */
    public SpannableString getClickable() {
        SpannableString spannableString = new SpannableString("忘记密码");
        //设置下划线文字
        spannableString.setSpan(new UnderlineSpan(), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //本界面弹框提示“忘记密码”
                //Toast.makeText(SignInActivity.this, "忘记密码", Toast.LENGTH_SHORT).getView();
            }
        }, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }

}
