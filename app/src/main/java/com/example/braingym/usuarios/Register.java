package com.example.braingym.usuarios;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.braingym.Inicio;
import com.example.braingym.R;
import com.example.braingym.backend.ApiClient;
import com.example.braingym.backend.ApiService;
import com.example.braingym.backend.AuthResponse;
import com.example.braingym.backend.RegisterRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.SharedPreferences;
import android.util.Log;

public class Register extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView imageView = findViewById(R.id.Gugleboton);
        TextView textView = findViewById(R.id.AlLogin);
        Button button = findViewById(R.id.RegisterButton);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);

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
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest(username, password, email);

        ApiService apiService = ApiClient.getApiService();
        Call<AuthResponse> call = apiService.register(registerRequest);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    saveUserInfo(username, email, token);
                    Toast.makeText(Register.this, "Has sido registrado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Inicio.class);
                    startActivity(intent);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("RegisterError", "Registro fallido: " + errorBody);
                        Toast.makeText(Register.this, "Registro fallido!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("RegisterError", "Error: " + t.getMessage());
                Toast.makeText(Register.this, "Ocurrió un error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserInfo(String username, String email, String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("token", token);
        editor.apply();
    }
}

