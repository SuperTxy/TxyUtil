package com.txy.txyutils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by txy on 2018/3/25.
 */

public class ObjectUtils {

    private ObjectUtils() {
    }

    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(final Object obj) {
        return !isEmpty(obj);
    }

    public static boolean equals(final Object obj1, final Object obj2) {
        return obj1 == obj2 || (obj1 != null && obj1.equals(obj2));
    }

    public static <T> T requireNonNull(final T obj,final String message){
        if(obj == null) {
            throw  new NullPointerException(message);
        }
        return obj;
    }

    public static <T> T getOrDefault(final T obj,final T defaultObj){
        if(obj == null) {
            return defaultObj;
        }
        return obj;
    }
    /**
     * @param list
     * @param t
     * @param <T>
     * @return the index of t in list
     */
    public static <T> int indexOfObj(List<T> list, T t) {
        if (!isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == t) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static <T> int size(Collection<T> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * @param t
     * @param <T>
     * @return a list that contains t
     */
    public static <T> List<T> fromObj2List(T t) {
        List<T> list = new ArrayList<>();
        list.add(t);
        return list;
    }

}
