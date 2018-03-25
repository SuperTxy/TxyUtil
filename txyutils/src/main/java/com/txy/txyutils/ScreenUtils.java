package com.txy.txyutils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by txy on 2018/3/24.
 */

public class ScreenUtils {

    private ScreenUtils() {
    }

    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    public static int px2dp(Context context,final int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int sp2px(Context context, int sp) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (density * sp + 0.5f);
    }

    public static int px2sp(Context context,final int px) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / density + 0.5f);
    }

    /**
     *
     * @param context
     * @return the height of screen ,in pixel
     */
    public static int getScreenHeight(Context context) {
      WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        }else{
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /**
     *
     * @param context
     * @return the width of screen ,in pixel
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if(Build.VERSION.SDK_INT >Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        }else{
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    public static void setFullScreen(@NonNull final Activity activity){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        |WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) statusBarHeight = context.getResources().getDimensionPixelOffset(resId);
        return statusBarHeight;
    }

    /**
     * 判断是否是平板
     * @return
     */
    public static boolean isTablet(Context context){
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static Bitmap screenShot(@NonNull final Activity activity){
        return screenShot(activity,false);
    }

    /**
     * @param activity
     * @param isDelStatusBar
     * @return return the bitmap of screen
     */
    public static Bitmap screenShot(@NonNull final Activity activity, boolean isDelStatusBar) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap bitmap = decorView.getDrawingCache();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap ret ;
        if(isDelStatusBar) {
            int statusBarHeight = getStatusBarHeight(activity);
            ret = Bitmap.createBitmap(bitmap,0, statusBarHeight,dm.widthPixels,dm.heightPixels - statusBarHeight);
        }else{
            ret = Bitmap.createBitmap(bitmap,0,0,dm.widthPixels,dm.heightPixels);
        }
        decorView.destroyDrawingCache();
        return ret;
    }

    public View getView(int layoutId, Context context) {
        return View.inflate(context, layoutId, null);
    }
}
