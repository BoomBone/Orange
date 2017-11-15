package com.boombone7.core.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.util.HashMap;

/**
 * Created by Ting on 2017/11/9.
 */

public class Orange {
    public static Configurator init(Context context){
        getConfigs().put(Configkey.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }
    private static HashMap<String,Object> getConfigs(){
        return Configurator.getInstance().getConfig();
    }

    public static <T> T getConfiguration(String key){
        return Configurator.getInstance().getConfiguration(key);
    }
}
