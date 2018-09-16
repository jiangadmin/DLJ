package com.jiang.dlj;

import android.app.Application;
import android.content.Context;

/**
 * @author: jiangadmin
 * @date: 2018/9/14
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:
 */
public class MyApp extends Application {

    static Context instance;
    public static boolean LogShow = true;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
    super.onCreate();
        instance = this;
    }
}
