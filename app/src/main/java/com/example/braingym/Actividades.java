package com.example.braingym;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.actividades.CalculoMental;
import com.example.braingym.actividades.PalabrasEncadenadas;
import com.example.braingym.actividades.PalabrasDesordenadas;
import com.example.braingym.actividades.Secuencias;
import com.example.braingym.usuarios.Perfil;

public class Actividades extends AppCompatActivity {
    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button Acalculomental = findViewById(R.id.Acalculomental);
        Button aPalabrasEncadenadas = findViewById(R.id.ApalabrasEncadenadas);
        Button Asecuencias = findViewById(R.id.Asecuencias);
        Button ApalabrasDesordenadas = findViewById(R.id.ApalabrasDesordenadas);

        Acalculomental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Actividades.this, CalculoMental.class);
                startActivity(intent);
            }
        });

        aPalabrasEncadenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Actividades.this, PalabrasEncadenadas.class);
                startActivity(intent);
            }
        });

        Asecuencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Actividades.this, Secuencias.class);
                startActivity(intent);
            }
        });

        ApalabrasDesordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Actividades.this, PalabrasDesordenadas.class);
                startActivity(intent);
            }
        });

    }

    public void expandirContenido(View view) {
        LinearLayout contenido = findViewById(R.id.contenidoAcalculomental);
        if (contenido.getVisibility() == View.VISIBLE) {
            contenido.setVisibility(View.GONE);
        } else {
            contenido.setVisibility(View.VISIBLE);

        }
    }

    public void expandirContenido2(View view) {
        LinearLayout contenido = findViewById(R.id.contenidoaPalabrasEncadenadas);
        if (contenido.getVisibility() == View.VISIBLE) {
            contenido.setVisibility(View.GONE);
        } else {
            contenido.setVisibility(View.VISIBLE);
        }
    }
    public void expandirContenido3(View view) {
        LinearLayout contenido = findViewById(R.id.contenidoAsecuencias);
        if (contenido.getVisibility() == View.VISIBLE) {
            contenido.setVisibility(View.GONE);
        } else {
            contenido.setVisibility(View.VISIBLE);
        };
    }
    public void expandirContenido4(View view) {
        LinearLayout contenido = findViewById(R.id.contenidoApalabrasDesordenadas);
        if (contenido.getVisibility() == View.VISIBLE) {
            contenido.setVisibility(View.GONE);
        } else {
            contenido.setVisibility(View.VISIBLE);
        }
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
            Intent intent = new Intent(Actividades.this, Perfil.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_menu) {
            Intent intent = new Intent(Actividades.this, MenuPrincipal.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
