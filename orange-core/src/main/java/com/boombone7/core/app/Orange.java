package com.boombone7.core.app;

import android.content.Context;

import com.boombone7.core.I;

import java.util.HashMap;

/**
 * @author Ting
 * @date 2017/11/9
 */

public final class Orange {
    public static Configurator init(Context context) {
        getConfigs().put(I.Configkey.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigs() {
        return Configurator.getInstance().getConfig();
    }

    public static <T> T getConfiguration(String key) {
        return Configurator.getInstance().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(I.Configkey.APPLICATION_CONTEXT);
    }
}
