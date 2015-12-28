package com.rm.oscatalog.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class AssetsUtil {

    private static final String FILE_EXT = ".json";
    private static AssetManager sAssets;

    public static void init(Context context) {
        sAssets = context.getAssets();
    }

    public static String readPageFromFile(String key) throws IOException {
        StringBuilder categoriesJson = new StringBuilder();

        InputStream rawCategories = sAssets.open(key + FILE_EXT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(rawCategories));

        String line;
        while ((line = reader.readLine()) != null) categoriesJson.append(line);

        return categoriesJson.toString();
    }
}
