package com.jwshi.supermummy.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * @Description：
 * @ Created by David.Shi on 2020/3/18.
 */
public class ScreenAdapter {


    private static float sNoncompatDensity = 0f;
    private static float sNoncompatScaledDensity = 0f;

    private static float sCalculateDensity = 0f;

    public static void calcuteDensity(int ori, Activity activity, Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            sCalculateDensity = sNoncompatDensity;
        }

        final float targetDensity;
        if (ori == Configuration.ORIENTATION_PORTRAIT) {
            targetDensity = appDisplayMetrics.widthPixels / 800f;
        } else {
            targetDensity = appDisplayMetrics.heightPixels / 800f;
        }
        final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        final DisplayMetrics activityDisplay = activity.getResources().getDisplayMetrics();
        activityDisplay.density = targetDensity;
        activityDisplay.scaledDensity = targetScaledDensity;
        activityDisplay.densityDpi = targetDensityDpi;
        sCalculateDensity = targetDensity;
    }


    public static float dp2px(int dp) {
        return 0.5f + dp * sCalculateDensity;
    }

    public static float px2dp(int px) {
        return px / sCalculateDensity;
    }
}
