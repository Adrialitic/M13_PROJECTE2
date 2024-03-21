package com.example.braingym;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Iniciosesion extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

        ImageView imageView = findViewById(R.id.Gugleboton);
        TextView textView = findViewById(R.id.AlRegistro);
        Button button = findViewById(R.id.loginButton);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com/"));
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Iniciosesion.this, Registro.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Iniciosesion.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });


    }
}
