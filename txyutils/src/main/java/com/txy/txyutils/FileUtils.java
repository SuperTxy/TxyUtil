package com.txy.txyutils;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.MEDIA_MOUNTED;
import static android.os.Environment.getExternalStorageState;

/**
 * Created by txy on 2018/3/25.
 * 适用于拍照或摄像创建文件
 */

public class FileUtils {

    /**
     * create a tmp file for image
     * @param context
     * @return
     * @throws IOException
     */
    public static File createIMGFile(Context context) throws IOException {
        return createTmpFile(context, "IMG", ".jpg");
    }

    /**
     * create a temp file for video
     * @param context
     * @return
     * @throws IOException
     */
    public static File createVIDFile(Context context) throws IOException {
        return createTmpFile(context, "VID", ".mp4");
    }

    public static File createTmpFile(Context context, String prefix, String suffix)  {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
        prefix = prefix+"_" + date + "_";
        File file = null;
        try {
          file = File.createTempFile(prefix, suffix, getFileDir(context));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getFileDir(Context context){
        File dir;
        if (TextUtils.equals(getExternalStorageState(), MEDIA_MOUNTED)) {
            dir = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM + "/Camera");
            if (!dir.exists()) {
                dir = getCacheDirectory(context, true);
            }
        } else {
            dir = getCacheDirectory(context, true);
        }
        return dir;
    }

    private static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        String externalStorageState;
        try {
            externalStorageState = getExternalStorageState();
        } catch (NullPointerException | IncompatibleClassChangeError e) {
            externalStorageState = "";
        }
        if (preferExternal && TextUtils.equals(externalStorageState, MEDIA_MOUNTED) && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = context.getFilesDir().getPath() + context.getPackageName() + "/cache/";
            appCacheDir = new File(cacheDirPath);
        }
        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appCacheDir;

    }

    private static boolean hasExternalStoragePermission(Context context) {
        return PERMISSION_GRANTED == context.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
}
