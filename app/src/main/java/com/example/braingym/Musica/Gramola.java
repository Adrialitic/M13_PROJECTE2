package com.example.braingym.Musica;

import android.app.Application;

public class Gramola extends Application {

    private static MusicaFondo musicaFondo;

    @Override
    public void onCreate() {
        super.onCreate();
        musicaFondo = new MusicaFondo(this);
        float savedVolume = VolumenPreferencia.getVolume(this);
        musicaFondo.setVolume(savedVolume);
        musicaFondo.start();
    }

    public static MusicaFondo getBackgroundMusic() {
        return musicaFondo;
    }
}