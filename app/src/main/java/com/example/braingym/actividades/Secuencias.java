package com.example.braingym.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.braingym.Actividades;
import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.R;

import java.util.Random;

public class Secuencias extends AppCompatActivity {
    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();
    private TextView sequenceTextView, scoreTextView, timerTextView, resultTextView;
    private EditText answerEditText;
    private Button checkButton, backToActivitiesButton;
    private int score = 0;
    private int questionCount = 0;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 120000; // 2 minutos

    private String[] sequences = {
            "2, 4, 6, 8, ?", "1, 3, 5, 7, ?", "5, 10, 15, 20, ?",
            "10, 20, 30, 40, ?", "1, 4, 7, 10, ?", "3, 6, 9, 12, ?",
            "2, 5, 8, 11, ?", "4, 8, 12, 16, ?", "6, 12, 18, 24, ?",
            "1, 2, 4, 8, ?", "7, 14, 21, 28, ?", "1, 1, 2, 3, 5, ?",
            "2, 3, 5, 7, 11, ?", "10, 15, 20, 25, ?", "2, 6, 18, 54, ?",
            "3, 5, 7, 9, ?", "5, 25, 125, ?",
            "1, 10, 100, ?", "3, 6, 12, 24, ?", "5, 7, 11, 13, 17, ?", "2, 4, 8, 16, 32, ?",
            "1, 2, 3, 5, 8, ?", "0, 1, 1, 2, 3, 5, ?", "3, 9, 27, ?",
            "8, 16, 24, 32, ?", "1, 4, 9, 16, ?", "5, 15, 45, ?",
            "2, 3, 5, 8, ?", "11, 22, 33, 44, ?", "7, 14, 28, 56, ?",
            "2, 5, 10, 17, ?", "1, 8, 27, ?", "4, 6, 9, 13, ?",
            "10, 30, 60, ?", "2, 6, 12, 20, ?", "7, 21, 43, ?",
            "1, 1, 2, 6, ?", "2, 3, 6, 11, ?", "10, 20, 35, 50, ?",
            "3, 6, 18, ?", "5, 10, 25, 50, ?", "4, 9, 16, 25, ?",
            "2, 7, 14, 23, ?", "10, 25, 50, 85, ?", "1, 4, 8, 13, ?",
            "3, 8, 15, 24, ?", "5, 15, 30, 50, ?", "6, 18, 36, 60, ?",
            "7, 10, 14, 19, ?"
    };
    private int[] answers = {
            10, 9, 25, 50, 13, 15, 14, 20, 30, 16,
            35, 8, 13, 30, 162, 11, 625, 1000, 48, 19, 64,
            13, 8, 81, 40, 25, 135, 13, 55, 112, 26, 64,
            17, 120, 21, 27, 14, 55, 17, 120, 24, 41,
            90, 48, 35, 22, 9, 24, 65, 85
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secuencias);

        sequenceTextView = findViewById(R.id.sequenceTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);
        answerEditText = findViewById(R.id.answerEditText);
        checkButton = findViewById(R.id.checkButton);
        backToActivitiesButton = findViewById(R.id.backToActivitiesButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        backToActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(Secuencias.this, Actividades.class);
                        startActivity(intent);
                finish();
            }
        });

        startGame();
    }

    private void startGame() {
        score = 0;
        questionCount = 0;
        startTimer();
        showNextSequence();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                timerTextView.setText(String.format("Tiempo: %02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                checkButton.setEnabled(false);
                resultTextView.setText("Tiempo terminado! Puntuación final: " + score);
                checkButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void endGame() {
        countDownTimer.cancel();
        resultTextView.setText("Juego terminado. Puntuación final: " + score);
        answerEditText.setEnabled(false);
        checkButton.setEnabled(false);
        backToActivitiesButton.setVisibility(View.VISIBLE);
    }
    
    private void showNextSequence() {
        if (questionCount >= 10) {
            endGame();
            return;
        }
        Random random = new Random();
        int index = random.nextInt(sequences.length);
        sequenceTextView.setText("Secuencia: " + sequences[index]);
        answerEditText.setTag(answers[index]);
        questionCount++;
    }

    private void checkAnswer() {
        String answer = answerEditText.getText().toString();
        if (answer.isEmpty()) {
            resultTextView.setText("Por favor, introduce una respuesta.");
            return;
        }

        int userAnswer = Integer.parseInt(answer);
        int correctAnswer = (int) answerEditText.getTag();

        if (userAnswer == correctAnswer) {
            score += 100;
            resultTextView.setText("¡Correcto!");
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            animateCorrect();
        } else {
            score -= 25;
            resultTextView.setText("Incorrecto. La respuesta correcta es " + correctAnswer);
            resultTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            animateIncorrect();
        }

        scoreTextView.setText("Puntuación: " + score);
        answerEditText.setText("");
        showNextSequence();
    }

    private void animateCorrect() {
        Animation correctAnimation = new AlphaAnimation(0.0f, 1.0f);
        correctAnimation.setDuration(300);
        correctAnimation.setRepeatCount(2);
        correctAnimation.setRepeatMode(Animation.REVERSE);
        resultTextView.startAnimation(correctAnimation);
    }

    private void animateIncorrect() {
        Animation incorrectAnimation = new AlphaAnimation(0.0f, 1.0f);
        incorrectAnimation.setDuration(300);
        incorrectAnimation.setRepeatCount(2);
        incorrectAnimation.setRepeatMode(Animation.REVERSE);
        sequenceTextView.startAnimation(incorrectAnimation);
    }
}
