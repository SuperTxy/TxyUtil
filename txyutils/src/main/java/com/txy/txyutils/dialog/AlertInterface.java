package com.txy.txyutils.dialog;

/**
 * Created by txy on 2018/3/27.
 */

public interface AlertInterface {

    DialogUtils.AlertBuilder setTitle(String title);
    DialogUtils.AlertBuilder setMessage(String message);
    DialogUtils.AlertBuilder setLeftButton(String leftStr, OnClickListener listener);
    DialogUtils.AlertBuilder setRightButton(String rightStr, OnClickListener listener);
    DialogUtils show();
    DialogUtils.AlertBuilder setOnButton(String str, OnClickListener listener);

    interface OnClickListener{
        void onClick();
    }
}
