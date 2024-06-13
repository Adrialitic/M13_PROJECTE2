package com.example.braingym.Musica;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.braingym.R;

public class MusicaFondo {
    private MediaPlayer mediaPlayer;
    private Context context;

    public MusicaFondo(Context context) {
        this.context = context;
        mediaPlayer = MediaPlayer.create(context, R.raw.fondo);
        mediaPlayer.setLooping(true);
    }

    public void start() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(context, R.raw.fondo);
        }
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void setVolume(float volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume, volume);
        }
    }
}
