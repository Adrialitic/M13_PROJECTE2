package com.example.braingym;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Inicio extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        AnimacionUno();
        AnimacionDos();

        Button iniciarSesionButton = findViewById(R.id.button);

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, Iniciosesion.class);
                startActivity(intent);
            }
        });

        Button registroButton = findViewById(R.id.button2);

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, Registro.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.twitter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
                startActivity(intent);
            }
        });

        ImageView imageView2 = findViewById(R.id.insta);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/"));
                startActivity(intent);
            }
        });
        TextView textView = findViewById(R.id.accesoninja);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, Menu.class);
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
}
