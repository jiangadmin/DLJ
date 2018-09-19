package com.jiang.dlj.iris;

import android.util.Log;

import java.util.List;

/**
 * Created by Qicj on 2017/10/16 0016.
 */

public class DBHelper {

    /**
     * 获取最小用户缺省值
     *
     * @return
     */
    public static int getMinimalDefaultValue(List<IrisUserInfo> users) {
        if (users == null || users.size() == 0) {
            return 0;
        }
        int minDefault = -1;
        for (int i = 0; i < 6; i++) {
            boolean hasUser = false;
            for (IrisUserInfo user : users) {
                Log.i("tony", "default: " + user.m_UserFavicon);
                if (user.m_UserFavicon == i) {
                    hasUser = true;
                    break;
                }
            }
            if (!hasUser) {
                minDefault = i;
                break;
            }
        }
        return minDefault;
    }

}
