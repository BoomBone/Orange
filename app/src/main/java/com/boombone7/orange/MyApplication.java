package com.boombone7.orange;

import android.app.Application;

import com.boombone7.core.app.Orange;
import com.boombone7.core.net.interceptors.DebugInterceptor;

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
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
