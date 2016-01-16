package com.rm.oscatalog.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.rm.oscatalog.model.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// утил класс для работы с файлами
public final class FileUtils {

    private static final String FILE_EXT = ".json"; // константа с расширением файлов

    // класс, дающий доступ к некомпилируемым файлам,
    // хранящимся в памяти приложения
    private static AssetManager sAssets;
    private static Context sContext;

    // инициализация
    public static void init(Context context) {
        sContext = context;
        sAssets = context.getAssets(); // получение доступа
    }

    // получение изображения по имени файла (используется для обложки видео)
    public static Drawable loadImageFromFile(String imageName) {
        try {
            InputStream ims = sAssets.open(imageName); // получение стрима по имени файла
            return Drawable.createFromStream(ims, null); // получение изображения из стрима
        }
        catch(IOException ex) {
            ex.printStackTrace(); // описание стэка ошибки

            // этот блок не должен быть досягаем, в случае если он достигнут,
            // для упрощения отладки здесь вызывается ошибка с уточняющим сообщением
            throw new IllegalArgumentException("Could not find image file");
        }
    }

    // получение описания страницы в формате JSON
    // файлы с описанием хранятся в памяти приложения
    // возвращает строку, в которую записано содержимое нужного файла
    public static String readJsonFromFile(String name) throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();

        // открытие стрима по имени файла
        InputStream jsonStream = sAssets.open(name + FILE_EXT);

        // формирование текстового стрима
        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream));

        // чтение стрима
        String line;
        while ((line = reader.readLine()) != null) jsonStringBuilder.append(line);

        return jsonStringBuilder.toString();
    }

    public static String getMimeType(Uri uri) {
        String mimeType;

        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = sContext.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase()
            );
        }
        return mimeType;
    }

    public static File getFileForDocument(Document item) {
        return new File(sContext.getExternalCacheDir(), item.toString());
    }
}
