package com.txy.txyutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import com.txy.txyutils.R;

/**
 * Created by txy on 2018/3/27.
 */

public class DialogUtils {

    private Dialog dialog;

    public DialogUtils (Context context) {
      this(context,R.layout.layout_alert_dialog);
    }

    public DialogUtils (Context context,int resId) {
      this(context,View.inflate(context,resId,null));
    }

    public DialogUtils (Context context,View contentView) {
        dialog = new Dialog(context, R.style.mydialogstyle);
        dialog.setContentView(contentView);
    }

    public void setCanceledOnTouchOutside(boolean canceled){
        dialog.setCanceledOnTouchOutside(canceled);
    }

    public static class AlertBuilder implements AlertInterface {

        private AlertController controller;
        private final DialogUtils dialogUtils;

        public AlertBuilder(Context context) {
            controller = new AlertController(context);
            dialogUtils = new DialogUtils(context);
            dialogUtils.setCanceledOnTouchOutside(false);
        }

        @Override
        public AlertBuilder setTitle(@NonNull String title) {
            controller.setTitle(title);
            return this;
        }

        @Override
        public AlertBuilder setMessage(@NonNull String message) {
            controller.setMessage(message);
            return this;
        }

        @Override
        public AlertBuilder setLeftButton(@NonNull String leftStr, OnClickListener listener) {
            controller.setLeftButton(leftStr,listener,dialogUtils.dialog);
            return this;
        }

        @Override
        public AlertBuilder setRightButton(@NonNull String rightStr, OnClickListener listener) {
            controller.setRightButton(rightStr,listener,dialogUtils.dialog);
            return this;
        }

        @Override
        public DialogUtils show() {
            dialogUtils.dialog.show();
            return dialogUtils;
        }

        @Override
        public AlertBuilder setOnButton(String str, OnClickListener listener) {
             controller.setOneButton(str,listener,dialogUtils.dialog);
             return this;
        }
    }
}
