package com.xpf.booksapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xpf.booksapp.R;
import com.xpf.booksapp.base.BaseFragment;
import com.xpf.booksapp.fragment.BookFragment;
import com.xpf.booksapp.fragment.MagazineFragment;
import com.xpf.booksapp.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

// 主页面
public class HomeActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridLayout gridLayout;
    private LinearLayout linear_tag;
    private TextView up;
    private Button menu_6, menu_1, menu_2, menu_3, menu_4, menu_5;
    private boolean isButton = true;

    private RadioGroup rgMain;
    private RadioButton rbRecommend, rbBook, rbMagazine;
    private FragmentTransaction transaction;
    private List<BaseFragment> fragments;
    private List<BaseFragment> cardFragments;
    private int currentPosition = 0; // 默认为位置为0
    private Fragment tempFragment; // 用于存储临时的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         * 以下为滑动侧菜单效果
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);//去除默认navigationItem的图标

        /**
         * 以下为默认隐藏顶部网格图标，点击更多后 显示，并隐藏文字标签
         */
        gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        menu_6 = (Button) findViewById(R.id.menu_6);
        up = (TextView) findViewById(R.id.up);
        linear_tag = (LinearLayout) findViewById(R.id.linear_tag);
        rgMain = (RadioGroup) findViewById(R.id.rgMain);
        rbRecommend = (RadioButton) findViewById(R.id.rbRecommend);
        rbBook = (RadioButton) findViewById(R.id.rbBook);
        rbMagazine = (RadioButton) findViewById(R.id.rbMagazine);

        initFragment();
        setOnClickListener();
        initListener();
        rgMain.check(R.id.rbRecommend); // 默认选中为我推荐
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new BookFragment());
        fragments.add(new MagazineFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbRecommend:
                        currentPosition = 0;
                        break;
                    case R.id.rbBook:
                        currentPosition = 1;
                        break;
                    case R.id.rbMagazine:
                        currentPosition = 2;
                        break;
                }
                BaseFragment fragment = getFragmentByPosition(currentPosition);
                switchFragment(tempFragment, fragment);
            }
        });
    }

    private void switchFragment(Fragment fromFragment, Fragment toFragment) {

        if (tempFragment != toFragment) {
            tempFragment = toFragment;
            if (toFragment != null) {
                // 开启事务
                transaction = this.getSupportFragmentManager().beginTransaction();

                // 判断要显示的Fragment是否已经被添加
                if (!toFragment.isAdded()) {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment); // 隐藏当前的
                    }
                    transaction.add(R.id.flContainer, toFragment).commit(); // 添加新的
                } else {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(toFragment).commit();
                }
            }
        }
    }

    private BaseFragment getFragmentByPosition(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    private void setOnClickListener() {
        menu_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton) {
                    gridLayout.setVisibility(View.VISIBLE);
                    if (isButton) {
                        linear_tag.setVisibility(View.GONE);
                        isButton = false;
                    }
                    isButton = false;
                } else {
                    gridLayout.setVisibility(View.GONE);
                    isButton = true;
                }
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton) {
                    gridLayout.setVisibility(View.VISIBLE);
//                    if (isButton) {
//                        linear_tag.setVisibility(View.GONE);
//                        isButton = false;
                    isButton = false;
                } else {
                    gridLayout.setVisibility(View.GONE);
                    isButton = true;
                    if (isButton) {
                        linear_tag.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_book) {
            // Handle the camera action
            Intent intent = new Intent(this, MyBookActivity.class);
            startActivity(intent);
            //跳转到 我的订阅
        } else if (id == R.id.my_collection) {
            Intent intent = new Intent(this, MyCollectionActivity.class);
            startActivity(intent);
            //跳转到 我的收藏
        } else if (id == R.id.my_like) {
            Intent intent = new Intent(this, MyLikeActivity.class);
            startActivity(intent);
            //跳转到 我的爱好
        } else if (id == R.id.setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            //跳转到 设置
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
