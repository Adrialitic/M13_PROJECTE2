package com.example.braingym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;

public class MainActivity extends Activity {
    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);

                finish();
            }
        }, 5000); //Cambiar a 5000 con la app ya acabada

        }
    }
