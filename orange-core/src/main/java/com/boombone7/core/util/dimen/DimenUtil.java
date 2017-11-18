package com.boombone7.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.boombone7.core.app.Orange;

/**
 * @author Ting
 * @date 2017/11/18.
 * @function 测量屏幕宽高
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Orange.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Orange.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
