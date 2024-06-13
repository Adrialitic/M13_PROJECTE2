package com.example.braingym.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.braingym.Actividades;
import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.R;

public class PalabrasEncadenadas extends AppCompatActivity {
    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();
    private TextView tvLastWord;
    private EditText etNewWord;
    private TextView tvStatus;
    private TextView tvScore;
    private TextView tvTimer;
    private Button btnSubmit;

    private String lastWord = "inicio";
    private int score = 0;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasencadenadas);

        tvLastWord = findViewById(R.id.tv_last_word);
        etNewWord = findViewById(R.id.et_new_word);
        tvStatus = findViewById(R.id.tv_status);
        tvScore = findViewById(R.id.tv_score);
        tvTimer = findViewById(R.id.tv_timer);
        btnSubmit = findViewById(R.id.btn_submit);

        tvLastWord.setText(lastWord);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWord = etNewWord.getText().toString().trim().toLowerCase();
                if (newWord.isEmpty()) {
                    showError("Por favor, ingrese una palabra.");
                } else if (newWord.charAt(0) != lastWord.charAt(lastWord.length() - 1)) {
                    score -= 25;
                    updateScore();
                    showError("La palabra debe comenzar con la letra " + lastWord.charAt(lastWord.length() - 1));
                } else {
                    score += 100;
                    updateScore();
                    lastWord = newWord;
                    tvLastWord.setText(lastWord);
                    etNewWord.setText("");
                    tvStatus.setText("");
                    showSuccess("Palabra aceptada");
                }
            }
        });

        startTimer();
    }

    private void updateScore() {
        tvScore.setText("Puntuación: " + score);
    }

    private void startTimer() {
        timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                tvTimer.setText(String.format("Tiempo: %02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void endGame() {
        tvStatus.setText("Tiempo acabado. Puntuación final: " + score);
        btnSubmit.setEnabled(false);

        Button btnRestart = new Button(this);
        btnRestart.setText("Volver a Actividades");
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PalabrasEncadenadas.this, Actividades.class);
                startActivity(intent);
                finish();
            }
        });

        ((LinearLayout) findViewById(R.id.linear_layout)).addView(btnRestart);
    }

    private void showError(String message) {
        tvStatus.setText(message);
        applyAnimation(tvStatus, false);
    }

    private void showSuccess(String message) {
        tvStatus.setText(message);
        applyAnimation(tvStatus, true);
    }

    private void applyAnimation(View view, boolean isCorrect) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);

        if (isCorrect) {
            view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        } else {
            view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }, 1500);
    }
}
