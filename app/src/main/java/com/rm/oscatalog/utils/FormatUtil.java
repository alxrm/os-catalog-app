package com.rm.oscatalog.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class FormatUtil {

    private static final String BYTE_PREFIXES = "KMGTPE";
    private static final String MD5_ENGINE_NAME = "MD5";

    public static String md5(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5_ENGINE_NAME);
            digest.update(message.getBytes(), 0, message.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot assembly md5 hash");
        }
    }

    public static String formatSeconds(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;

        String formedHours = (hours > 0) ? hours + ":" : "";
        String formedMins = (hours > 0 && minutes < 10 ? "0" : "") + minutes;
        String formedSecs = (seconds < 10 ? "0" : "") + minutes;

        return String.format("%s%s:%s", formedHours, formedMins, formedSecs);
    }

    public static String formatBytes(long bytes) {

        // если количество байт меньше килобайта, префикса нет, возвращаем просто количество байт
        if (bytes < 1024) return bytes + " B";

        // считаем по степени двойки и степени двойки в 10 степени отступ в списке префиксов
        int exp = (int) (Math.log(bytes) / Math.log(1024));

        // отступ смещается на единицу, потому что в строках индексация элементов начинается с нуля
        String pre = String.valueOf((BYTE_PREFIXES).charAt(exp - 1));

        /*
            форматируем всё в строку, где первый параметр это количество байтов,
            делённых на 1024 в равной префиксу степени
            и второй параметр -- префикс(Kilo, Mega, Giga, Tera) перед первой буквой слова Byte
        */
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }
}
