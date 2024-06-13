package com.example.braingym;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.Musica.VolumenPreferencia;
import com.example.braingym.usuarios.Perfil;

public class Configuracion extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView volumeTextView;
    private MusicaFondo musicaFondo;
    private Spinner spinnerLanguage;
    private Switch switchNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        seekBar = findViewById(R.id.BarraVolumen);
        volumeTextView = findViewById(R.id.VolumeTextView);
        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        switchNotifications = findViewById(R.id.switchNotifications);
        musicaFondo = Gramola.getBackgroundMusic();

        float savedVolume = VolumenPreferencia.getVolume(this);
        seekBar.setProgress((int) (savedVolume * 100));
        musicaFondo.setVolume(savedVolume);
        volumeTextView.setText("Volumen: " + (int) (savedVolume * 100) + "%");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100.0f;
                musicaFondo.setVolume(volume);
                VolumenPreferencia.saveVolume(Configuracion.this, volume);
                volumeTextView.setText("Volumen: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        switchNotifications.setChecked(areNotificationsEnabled());
        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveNotificationPreference(isChecked);
            }
        });
    }


    private boolean areNotificationsEnabled() {
        return false;
    }

    private void saveNotificationPreference(boolean enableNotifications) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicaFondo.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicaFondo.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicaFondo.stop();
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
            Intent intent = new Intent(Configuracion.this, Perfil.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_menu) {
            Intent intent = new Intent(Configuracion.this, MenuPrincipal.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

