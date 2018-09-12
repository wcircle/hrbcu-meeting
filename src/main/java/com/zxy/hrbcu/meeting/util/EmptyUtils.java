package com.zxy.hrbcu.meeting.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by 38091 on 2018/4/16.
 */
public class EmptyUtils {

    public static String toString(Object object) {
        return object == null ? "" : object.toString();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(String string) {
        return toString(string).isEmpty();
    }

    public static boolean isEmptyTrim(String string) {
        return toString(string).trim().isEmpty();
    }

    public static boolean isEmpty(Object object) {
        return toString(object).isEmpty();
    }

    public static boolean isEmptyTrim(Object object) {
        return toString(object).trim().isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
