package com.rm.oscatalog.utils;

// утил класс для форматирования данных в читабельный формат
public class FormatUtil {

    // константа с префиксами (Kilo, Mega, Giga, Tera)
    private static final String BYTE_PREFIXES = "KMGTPE";

    // форматирование времени в читабельный формат из количества секунд
    // используется в списке видео
    public static String formatSeconds(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
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
