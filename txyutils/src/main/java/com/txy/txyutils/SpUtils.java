package com.txy.txyutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;

import java.util.Collections;
import java.util.Set;

/**
 * Created by txy on 2018/3/24.
 */
public final class SpUtils {

    private static SimpleArrayMap<String,SpUtils> SP_UTILS_MAP = new SimpleArrayMap<>();
    private SharedPreferences sp;

    private SpUtils(Context context, String name) {
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public static SpUtils getInstance(Context context){
      return  getInstance(context,"SpUtils");
    }

    public static SpUtils getInstance(Context context, String name) {
        SpUtils spUtils = SP_UTILS_MAP.get(name);
        if(spUtils == null) {
            spUtils = new SpUtils(context,name);
            SP_UTILS_MAP.put(name,spUtils);
        }
        return spUtils;
    }

    public void put(@NonNull final String key,@NonNull final Object obj){
        put(key,obj,false);
    }

    public void put(String key, Object obj, boolean isCommit) {
        SharedPreferences.Editor editor = sp.edit();
        if(obj instanceof String) {
            editor = editor.putString(key, (String) obj);
        }else if(obj instanceof Integer) {
            editor = editor.putInt(key, (Integer) obj);
        }else if(obj instanceof Boolean) {
            editor = editor.putBoolean(key, (Boolean) obj);
        }else if(obj instanceof Float) {
            editor = editor.putFloat(key, (Float) obj);
        }else if(obj instanceof Long) {
            editor = editor.putLong(key, (Long) obj);
        }else{
            throw new IllegalArgumentException("不合法的value");
        }
        if(isCommit) {
            editor.commit();
        }else{
            editor.apply();
        }
    }

    public void put(@NonNull final String key, @NonNull final Set<String> set,boolean isCommit){
        if(isCommit) {
            sp.edit().putStringSet(key, set).commit();
        }else{
            sp.edit().putStringSet(key, set).apply();
        }
    }

    public String getString(@NonNull final String key){
        return getString(key,"");
    }

    public String getString(@NonNull final String key,@NonNull final String defValue) {
        return sp.getString(key,defValue);
    }
    public int getInt(@NonNull final String key){
        return getInt(key,-1);
    }

    public int getInt(@NonNull final String key, final int defValue) {
        return sp.getInt(key,defValue);
    }
    public boolean getBoolean(@NonNull final String key){
        return getBoolean(key,false);
    }

    public boolean getBoolean(@NonNull final String key, final boolean defValue) {
        return sp.getBoolean(key,defValue);
    }
    public long getLong(@NonNull final String key){
        return getLong(key,-1L);
    }

    public long getLong(@NonNull final String key, final long defValue) {
        return sp.getLong(key,defValue);
    }
    public float getFloat(@NonNull final String key){
        return getFloat(key,-1f);
    }

    public float getFloat(@NonNull final String key, final float defValue) {
        return sp.getFloat(key,defValue);
    }
    public Set<String> getStringSet(@NonNull final String key){
        return getStringSet(key, Collections.<String>emptySet());
    }

    public Set<String> getStringSet(@NonNull final String key,@NonNull final Set<String> defValue) {
        return sp.getStringSet(key,defValue);
    }
}
