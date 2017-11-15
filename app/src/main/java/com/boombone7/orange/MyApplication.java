package com.boombone7.orange;

import android.app.Application;

import com.boombone7.core.app.Orange;

/**
 *
 * @author Ting
 * @date 2017/11/15
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Orange.init(getApplicationContext())
                .withApplication("http://news.baidu.com/")
                .configure();
    }
}
