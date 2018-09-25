package com.jiang.dlj;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void Time() {
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse("2018-09-20 00:00:00");
            Date d2 = df.parse("2018-09-22 00:00:00");

            if (date.getTime() - d1.getTime() < 0) {
                System.out.println("未开始");
            } else if (date.getTime() - d2.getTime() > 0) {
                System.out.println("已超时");
            } else {
                long diff = d2.getTime() - date.getTime();//这样得到的差值是微秒级别
                long days = diff / (1000 * 60 * 60 * 24);

                long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                System.out.println((days * 24) + hours + "时" + minutes);
            }

        } catch (Exception e) {

        }
    }
}