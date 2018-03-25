package com.txy.txyutils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by txy on 2018/3/25.
 */

public class PermissionUtils {

    private Runnable mHasPermissionRunnable;
    private Runnable mNoPermissionRunnable;
    private int REQUEST_CODE_PERMISSION = 1000;
    private String[] recordPermissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] cameraPermissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] storagePermissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private String[] audioPermissions = {Manifest.permission.RECORD_AUDIO};
    private PermissionUtils() {
    }

    public static class Builder {
        private static final PermissionUtils instance = new PermissionUtils();
    }

    public static final PermissionUtils getInstance() {
        return Builder.instance;
    }

    public void checkPermisson(final Activity context,final @NonNull String[] permissons,final String reason,final Runnable hasPermissonDo){
        checkPermisson(context,permissons, hasPermissonDo, new Runnable() {
            @Override
            public void run() {
                showPermissionDialog(reason,context);
            }
        });
    }

    private void checkPermisson(final Activity context,final @NonNull String[] permissions,final Runnable hasPermissionDo,final Runnable noPermissonDo) {
        mHasPermissionRunnable = null;
        mNoPermissionRunnable = null;
        if(isPermissionGranted(permissions,context)) {
            hasPermissionDo.run();
        }else if(shouldShowRequestPermissionRationale(permissions,context)) {
            noPermissonDo.run();
        }else{
            ActivityCompat.requestPermissions(context, noGrantedPermissions(permissions,context), REQUEST_CODE_PERMISSION);
            mNoPermissionRunnable = hasPermissionDo;
            mHasPermissionRunnable = hasPermissionDo;
        }
    }

    public boolean isPermissionGranted(final String[] permissions,Activity context) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    private boolean shouldShowRequestPermissionRationale(final String[] permissions,final Activity activity) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
                return true;
        }
        return false;
    }

    private String[] noGrantedPermissions(String[] permissions,Activity context) {
        StringBuilder noPermissions = new StringBuilder();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                if (i != 0) noPermissions.append(",");
                noPermissions.append(permissions[i]);
            }
        }
        return noPermissions.toString().split(",");
    }

    private void showPermissionDialog(final String message,final Activity context){
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("取消",null)
                .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:"+context.getPackageName()));
                        context.startActivity(intent);
                    }
                }).show();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (isAllGranted(grantResults))
                mHasPermissionRunnable.run();
            else mNoPermissionRunnable.run();
        }
    }

    private boolean isAllGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

}
