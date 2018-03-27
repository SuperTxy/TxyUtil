package com.txy.txyutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.txy.txyutils.R;

/**
 * Created by txy on 2018/3/27.
 */

public class AlertController {

    TextView tvTitle;
    TextView tvMessage;
    TextView tvLeft;
    TextView tvRight;
    LinearLayout llDoubleButton;
    TextView tvOneOk;
    View contentView;

    AlertController(Context context) {
        contentView = View.inflate(context, R.layout.layout_alert_dialog,null);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        tvMessage = (TextView) contentView.findViewById(R.id.tv_message);
        tvLeft = (TextView) contentView.findViewById(R.id.tv_left);
        tvRight = (TextView) contentView.findViewById(R.id.tv_right);
        tvOneOk = (TextView) contentView.findViewById(R.id.tv_one_ok);
        llDoubleButton = (LinearLayout) contentView.findViewById(R.id.ll_double_button);
    }

    void show(String title, String message) {
        llDoubleButton.setVisibility(View.GONE);
        tvOneOk.setVisibility(View.VISIBLE);
        show(title,message,null,null);
    }

    void show(String title, String message, String left, String right) {
        tvMessage.setText(message);
        setText(tvLeft,left);
        setText(tvRight,right);
        setText(tvTitle, title);
    }

    public void setText(TextView tv, String str) {
        if (!TextUtils.isEmpty(str))
            tv.setText(str);
    }

    public void setTitle(String title) {
       tvTitle.setVisibility(View.VISIBLE);
       tvTitle.setText(title);
    }

    public View contentView() {
        return contentView;
    }

    public void setMessage(String message) {
        tvMessage.setText(message);
    }

    public void setLeftButton(String leftStr, final AlertInterface.OnClickListener listener, final Dialog dialog) {
        llDoubleButton.setVisibility(View.VISIBLE);
        tvLeft.setText(leftStr);
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick();
                }
                dialog.dismiss();
            }
        });
    }

    public void setRightButton(String rightStr, final AlertInterface.OnClickListener listener, final Dialog dialog) {
        llDoubleButton.setVisibility(View.VISIBLE);
        tvRight.setText(rightStr);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick();
                }
                dialog.dismiss();
            }
        });
    }

    public void setOneButton(String str, final AlertInterface.OnClickListener listener, final Dialog dialog) {
        tvOneOk.setVisibility(View.VISIBLE);
        tvOneOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick();
                }
                dialog.dismiss();
            }
        });
    }
}
