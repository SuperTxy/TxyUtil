package com.txy.txyutils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by txy on 2018/3/24.
 */

public class ToastUtils {
    private ToastUtils() {
    }

    public static void toastCenter(String str, Context context) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void toastCenter(int resId, Context context) {
        toastCenter(context.getString(resId), context);
    }

    public static void toast(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void toast(int resId, Context context) {
        toast(context.getString(resId), context);
    }

    public static void toastNoNetwork(Context context) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.nowangluo);
        toast.setView(imageView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
