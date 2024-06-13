package com.example.braingym.usuarios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.braingym.MainActivity;
import com.example.braingym.MenuPrincipal;
import com.example.braingym.R;

import java.io.IOException;

public class Perfil extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView fotoPerfil;
    private Uri imagenSeleccionada;
    private TextView usernameTextView;
    private TextView nombreUsuarioTextView;
    private TextView emailTextView;
    private TextView correoUsuarioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fotoPerfil = findViewById(R.id.FotoPerfil);
        usernameTextView = findViewById(R.id.usernameTextView);
        nombreUsuarioTextView = findViewById(R.id.NombreUsuario);
        emailTextView = findViewById(R.id.emailTextView);
        correoUsuarioTextView = findViewById(R.id.CorreoUsuario);
        Button botonCambiarFoto = findViewById(R.id.editProfileButton);

        botonCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarPermiso();
            }
        });

        cargarImagenGuardada();
        cargarInformacionUsuario();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.CerrarSesion);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void solicitarPermiso() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PICK_IMAGE_REQUEST);
        } else {
            abrirGaleria();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                // Handle permission denial
            }
        }
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagenSeleccionada = data.getData();

            // Take persistable URI permission
            getContentResolver().takePersistableUriPermission(imagenSeleccionada, Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                fotoPerfil.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), imagenSeleccionada));
                guardarImagenSeleccionada();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void guardarImagenSeleccionada() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("imagen_perfil", imagenSeleccionada.toString());
        editor.apply();
    }

    private void cargarImagenGuardada() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String uriString = prefs.getString("imagen_perfil", null);
        if (uriString != null) {
            imagenSeleccionada = Uri.parse(uriString);
            try {
                getContentResolver().takePersistableUriPermission(imagenSeleccionada, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                fotoPerfil.setImageURI(imagenSeleccionada);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarInformacionUsuario() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("username", "N/A");
        String email = prefs.getString("email", "N/A");

        nombreUsuarioTextView.setText(username);
        correoUsuarioTextView.setText(email);
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
            Intent intent = new Intent(Perfil.this, Perfil.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_menu) {
            Intent intent = new Intent(Perfil.this, MenuPrincipal.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
