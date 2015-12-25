package com.rm.oscatalog.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rm.oscatalog.model.PageData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class AssetsUtil {

    private static final String FILE_EXT = ".json";

    private static AssetManager sAssets;
    private static Gson sGson;

    private static String readPageFromFile(String key) throws IOException {
        StringBuilder categoriesJson = new StringBuilder();

        InputStream rawCategories = sAssets.open(key + FILE_EXT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(rawCategories));

        String line;
        while ((line = reader.readLine()) != null) categoriesJson.append(line);

        return categoriesJson.toString();
    }

    public static <T extends PageData> ArrayList<T> getPageDataByKey(Context context, String key) {
        if (sGson == null) sGson = new Gson();
        if (sAssets == null) sAssets = context.getAssets();

        Type t = new TypeToken<ArrayList<T>>() {}.getType();
        ArrayList<T> result = new ArrayList<>();

        try {
            result = sGson.fromJson(readPageFromFile(key), t);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
