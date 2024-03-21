package com.example.braingym;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Informes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            Intent intent = new Intent(Informes.this, Perfil.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_menu) {
            Intent intent = new Intent(Informes.this, MenuPrincipal.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }}
