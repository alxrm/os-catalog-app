package com.rm.oscatalog.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class Prefs {

    public static final String KEY_IS_HINT_SHOWN = "IS_HINT_SHOWN";
    private static final String PREF_MAIN_NAME = "os_catalog_prefs";

    private static SharedPreferences sPreferences;
    private static SharedPreferences.Editor sEditor;

    public static void init(Context context) {
        sPreferences = context.getSharedPreferences(PREF_MAIN_NAME, Context.MODE_PRIVATE);
        sEditor = sPreferences.edit();
    }

    //region Pref methods
    public static <T> void put(String key, T value) {

        if (value instanceof Integer) sEditor.putInt(key, (Integer) value);
        else if (value instanceof Long) sEditor.putLong(key, (Long) value);
        else if (value instanceof String) sEditor.putString(key, (String) value);
        else if (value instanceof Float) sEditor.putFloat(key, (Float) value);
        else if (value instanceof Boolean) sEditor.putBoolean(key, (Boolean) value);

        sEditor.commit();
    }

    public static SharedPreferences get() {
        return sPreferences;
    }
    //endregion
}
