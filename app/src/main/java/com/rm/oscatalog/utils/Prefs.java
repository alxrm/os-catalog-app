package com.rm.oscatalog.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* утил класс, используемый для работы с настройками */
public final class Prefs {

    /*
    ключ, по которому мы находим в настройках,
    показывалась ли уже подсказка при входе в приложение
    */
    public static final String KEY_IS_HINT_SHOWN = "IS_HINT_SHOWN";

    private static SharedPreferences sPreferences; /* класс, дающий доступ на чтение настроек */
    private static SharedPreferences.Editor sEditor; /* класс, дающий доступ на запись в настройки */

    // инициализация
    public static void init(Context context) {
        /* получения доступа к настройкам */
        sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sEditor = sPreferences.edit();
    }

    /* обобщённый метод записи в найстроки, принимающий ключ и значение */
    public static <T> void put(String key, T value) {

        /* в зависимости от типа значения, вызывается нужный метод записи */
        if (value instanceof Integer) sEditor.putInt(key, (Integer) value);
        else if (value instanceof Long) sEditor.putLong(key, (Long) value);
        else if (value instanceof String) sEditor.putString(key, (String) value);
        else if (value instanceof Float) sEditor.putFloat(key, (Float) value);
        else if (value instanceof Boolean) sEditor.putBoolean(key, (Boolean) value);

        /* сохранение изменений */
        sEditor.commit();
    }

    /* метод для получения настроек */
    public static SharedPreferences get() {
        return sPreferences;
    }
}
