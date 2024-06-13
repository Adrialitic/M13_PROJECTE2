package com.example.braingym.Musica;

import android.content.Context;
import android.content.SharedPreferences;

public class VolumenPreferencia {
    private static final String PREFS_NAME = "VolumePrefs";
    private static final String VOLUME_KEY = "volume";

    public static void saveVolume(Context context, float volume) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(VOLUME_KEY, volume);
        editor.apply();
    }

    public static float getVolume(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getFloat(VOLUME_KEY, 0.5f);
    }
}
