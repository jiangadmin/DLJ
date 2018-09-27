package com.jiang.dlj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiang.dlj.MyApp;
import com.jiang.dlj.R;
import com.jiang.dlj.adapter.Home_Item_Adapter;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.entity.Icon;
import com.jiang.dlj.entity.Save_Key;
import com.jiang.dlj.iris.Isir_Activity;
import com.jiang.dlj.utils.SaveUtils;
import com.jiang.dlj.utils.TabToast;
import com.tencent.bugly.beta.Beta;

import java.util.ArrayList;

public class Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    private static final String TAG = "Main_Activity";

    private GridView grid_photo;
    private Home_Item_Adapter mAdapter = null;
    private ArrayList<Icon> mData = null;

    TextView nav_name;

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        LinearLayout linearLayout = (LinearLayout) navigationView.inflateHeaderView(R.layout.nav_header_main);
        nav_name = linearLayout.findViewById(R.id.nav_name);
        navigationView.setNavigationItemSelectedListener(this);

        initview();
    }

    private void initview() {
        grid_photo = findViewById(R.id.main_item);

        mData = new ArrayList<>();
        mData.add(new Icon(R.drawable.ic_xunjian, "巡回检查"));
        mData.add(new Icon(R.drawable.ic_jiaojie, "交接班"));
        mData.add(new Icon(R.drawable.ic_gongzuo, "工作票"));
        mData.add(new Icon(R.drawable.ic_chaozuo, "操作票"));
        mData.add(new Icon(R.drawable.ic_jianpan, "监盘"));
        mData.add(new Icon(R.drawable.ic_quexian, "缺陷管理"));
        mData.add(new Icon(R.drawable.ic_weihu, "定期工作"));
        mData.add(new Icon(R.drawable.ic_weihu, "运行台账"));
        mData.add(new Icon(R.drawable.ic_weihu, "值班日志"));

        mAdapter = new Home_Item_Adapter(this);
        mAdapter.setmData(mData);

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(this);

        nav_name.setText(SaveUtils.getString(Save_Key.UserName));


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (mData.get(position).getiName()) {
            case "巡回检查":
                Tour_Route_Activity.start(this);
                break;
            default:
                TabToast.makeText("暂未开放");
                break;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Snackbar.make(drawer, "确认退出吗？", Snackbar.LENGTH_LONG)
                    .setAction("确认", v -> MyApp.AppExit()).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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
            case R.id.nav_input_eye:
                Isir_Activity.start(this);
                break;
            case R.id.nav_update_password:
                break;
            case R.id.nav_ready:
                Base_Dialog dialog = new Base_Dialog(this);
                dialog.setTitle("巡检准备");
                dialog.setMessage("可根据您对业务的熟练度选择提示或屏蔽巡检准备信息");
                dialog.setOk("屏蔽", v -> {
                    SaveUtils.setBoolean(Save_Key.S_巡检准备, true);
                    TabToast.makeText("已为您屏蔽");
                });
                dialog.setEsc("提示", v -> {
                    SaveUtils.setBoolean(Save_Key.S_巡检准备, false);
                    TabToast.makeText("已为您展示");
                });
                break;
            case R.id.nav_update:
                Beta.checkUpgrade();
                break;
            case R.id.nav_about:
                About_Activity.start(this);
                break;
            case R.id.nav_exit:
                Snackbar.make(grid_photo, "确认退出当前账号吗？", Snackbar.LENGTH_LONG)
                        .setAction("确认", v -> {
                            Login_Activity.start(Main_Activity.this);
                            MyApp.finishActivity();
                        }).show();

                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
