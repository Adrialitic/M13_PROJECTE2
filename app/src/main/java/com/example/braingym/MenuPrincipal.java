package com.example.braingym;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.usuarios.Perfil;

public class MenuPrincipal extends AppCompatActivity {
    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnimacionUno();
        AnimacionDos();

        Button aActividades = findViewById(R.id.Actividades);

        aActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Actividades.class);
                startActivity(intent);
            }
        });

        Button aInformes = findViewById(R.id.Informes);

        aInformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Informes.class);
                startActivity(intent);
            }
        });

        ImageView ASettings = findViewById(R.id.imageView5);

        ASettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Configuracion.class);
                startActivity(intent);
            }
        });
    }

    private void AnimacionUno(){

        //Declarar
        ImageView librosImageView = findViewById(R.id.libros);
        ImageView pesasImageView = findViewById(R.id.pesas);
        ImageView librosdosImageView = findViewById(R.id.librosdos);
        ImageView pesasdosImageView = findViewById(R.id.pesasdos);

        //Se crea la animación
        Animation animacion = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.1f
        ); // Poner reversa fiufiufiu
        animacion.setDuration(1000);
        animacion.setRepeatCount(Animation.INFINITE);
        animacion.setRepeatMode(Animation.REVERSE);

        //Se empieza
        librosImageView.startAnimation(animacion);
        pesasImageView.startAnimation(animacion);
        librosdosImageView.startAnimation(animacion);
        pesasdosImageView.startAnimation(animacion);

    }

    private void AnimacionDos() {

        //Declarar
        ImageView exclamacionImageView = findViewById(R.id.exclamacion);

        // Crear animación
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.2f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.2f, 1.0f);
        ObjectAnimator animacionDestacar = ObjectAnimator.ofPropertyValuesHolder(exclamacionImageView, scaleX, scaleY);
        animacionDestacar.setDuration(1000);
        animacionDestacar.setRepeatCount(ObjectAnimator.INFINITE);
        animacionDestacar.setRepeatMode(ObjectAnimator.REVERSE);

        //Se empieza
        animacionDestacar.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_perfil) {
            Intent intent = new Intent(MenuPrincipal.this, Perfil.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_menu) {
            Intent intent = new Intent(MenuPrincipal.this, MenuPrincipal.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}