package com.txy.txyutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by txy on 2018/3/25.
 */

public class KeyboardUtils {

    private static int contentViewInvisibleHeightPre;

    private KeyboardUtils() {
    }

    /**
     * show the soft input
     * @param activity
     */
    public static void showSoftInput(final Activity activity){
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(im == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if(view == null) {
            view = new View(activity);
        }
        im.showSoftInput(view,InputMethodManager.SHOW_FORCED);
    }

    /**
     * show the soft input
     * @param view
     */
    public static  void showSoftInput(View view){
       InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
    }

    /**
     * show the soft input
     * @param activity
     */
    public static void hideSoftInput(final Activity activity){
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(im == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if(view == null) {
            view = new View(activity);
        }
        im.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    /**
     * show the soft input
     * @param view
     */
    public static  void hideSoftInput(final View view){
       InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public static void toggleSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm == null) {
            return;
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    /**
     * whether soft input is visible
     * @param activity
     * @return
     */
    public static boolean isSoftInputVisible(final Activity activity){
        return isSoftInputVisible(activity,200);
    }

    /**
     *
     * @param activity
     * @param minHeightOfSoftInput
     * @return whether soft input is visible
     */
    public static boolean isSoftInputVisible(final Activity activity,final int minHeightOfSoftInput){
        return getContentViewInvisibleHeight(activity) >= minHeightOfSoftInput;
    }

    private static int getContentViewInvisibleHeight(final Activity activity){
        View contentView = activity.findViewById(android.R.id.content);
        Rect outRect = new Rect();
        contentView.getWindowVisibleDisplayFrame(outRect);
        return contentView.getBottom() - outRect.bottom;
    }

    /**
     * register soft input changed listener
     * @param activity
     * @param listener
     */
    public static  void registerSoftInputChangedListener(final Activity activity,
                                                         final OnSoftInputChangedListener listener){
        View contentView = activity.findViewById(android.R.id.content);
        contentViewInvisibleHeightPre = getContentViewInvisibleHeight(activity);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height  = getContentViewInvisibleHeight(activity);
                if(height!= contentViewInvisibleHeightPre) {
                    listener.onSoftInputChanged(height);
                    contentViewInvisibleHeightPre = height;
                }
            }
        });
    }

    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int height);
    }

    /**
     * Click the blank area to hide soft input
     * Copy the following code in activity.
     */

     /*
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS
                    );
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // Return whether touch the view.
        private boolean isShouldHideKeyboard(View v, MotionEvent event) {
            if (v != null && (v instanceof EditText)) {
                int[] l = {0, 0};
                v.getLocationInWindow(l);
                int left = l[0],
                        top = l[1],
                        bottom = top + v.getHeight(),
                        right = left + v.getWidth();
                return !(event.getX() > left && event.getX() < right
                        && event.getY() > top && event.getY() < bottom);
            }
            return false;
        }
        */
}
