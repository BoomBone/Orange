package com.boombone7.core.ui;

import android.content.Context;
import android.util.Log;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author Ting
 * @date 2017/11/18.
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type,Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type)==null){
            //关键
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String type){
        if(type==null||type.isEmpty()){
            return null;
        }
        //反射
        final StringBuilder className = new StringBuilder();
        if (!type.contains(".")){
            String name = AVLoadingIndicatorView.class.getPackage().getName();
            Log.e("main", name);
            className.append(name)
                    .append(".indicators")
                    .append(".");
        }
        className.append(type);

        try {
            Class<?> drawableClass = Class.forName(className.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
