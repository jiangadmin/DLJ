package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jiang.dlj.R;
import com.jiang.dlj.adapter.MyAdapter;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.entity.Icon;

import java.util.ArrayList;

public class Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    private static final String TAG = "Main_Activity";

    private GridView grid_photo;
    private MyAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Main_Activity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initview();
    }

    private void initview() {
        grid_photo = findViewById(R.id.main_item);

        mData = new ArrayList<>();
        mData.add(new Icon(R.mipmap.ic_launcher, "交接班"));
        mData.add(new Icon(R.mipmap.ic_launcher, "监盘"));
        mData.add(new Icon(R.mipmap.ic_launcher, "定期工作"));
        mData.add(new Icon(R.mipmap.ic_launcher, "值班日志"));
        mData.add(new Icon(R.mipmap.ic_launcher, "运行台账"));
        mData.add(new Icon(R.mipmap.ic_launcher, "缺陷管理"));
        mData.add(new Icon(R.mipmap.ic_launcher, "生产日报"));
        mData.add(new Icon(R.mipmap.ic_launcher, "操作票"));
        mData.add(new Icon(R.mipmap.ic_launcher, "工作票"));
        mData.add(new Icon(R.mipmap.ic_launcher, "巡回检查"));

        mAdapter = new MyAdapter(this);
        mAdapter.setmData(mData);

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Demo_Activity.start(this, mData.get(position).getiName());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Base_Dialog dialog = new Base_Dialog(this);
            dialog.setMessage("确认退出吗？");
            dialog.setOk("确认", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Main_Activity.super.onBackPressed();
                }
            });
            dialog.setEsc("取消", null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_manage:
                break;

            case R.id.nav_setting:
                Setting_Activity.start(this);
                break;
            case R.id.nav_exit:
                Base_Dialog dialog = new Base_Dialog(this);
                dialog.setMessage("确认退出当前账号吗？");
                dialog.setOk("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Login_Activity.start(Main_Activity.this);
                        finish();
                    }
                });
                dialog.setEsc("取消", null);
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
