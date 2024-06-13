package com.example.braingym.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.braingym.Actividades;
import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.R;

public class CalculoMental extends AppCompatActivity {

    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();
    private TextView questionTextView, resultTextView, scoreTextView, timerTextView;
    private EditText answerEditText;
    private Button submitButton, restartButton;
    private int score = 0, correctAnswer;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculomental);

        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitButton = findViewById(R.id.submitButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        restartButton = findViewById(R.id.restartButton);

        generateQuestion();
        startTimer();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculoMental.this, Actividades.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void generateQuestion() {
        int number1, number2;
        number1 = (int) (Math.random() * 100);
        number2 = (int) (Math.random() * 100);
        boolean isAddition = Math.random() > 0.5;

        if (isAddition) {
            correctAnswer = number1 + number2;
            questionTextView.setText(number1 + " + " + number2 + " = ?");
        } else {
            correctAnswer = number1 - number2;
            questionTextView.setText(number1 + " - " + number2 + " = ?");
        }
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString();
        if (!userAnswer.isEmpty()) {
            int userAnswerInt = Integer.parseInt(userAnswer);
            if (userAnswerInt == correctAnswer) {
                score += 100;
                resultTextView.setText("Correcto!");
                applyAnimation(resultTextView, true);
            } else {
                score -= 25;
                resultTextView.setText("Incorrecto. La respuesta correcta es " + correctAnswer);
                applyAnimation(resultTextView, false);
            }
            scoreTextView.setText("Puntuación: " + score);
            generateQuestion();
            answerEditText.setText("");
        } else {
            resultTextView.setText("Por favor ingresa una respuesta.");
        }
    }

    private void startTimer() {
        timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                timerTextView.setText(String.format("Tiempo: %02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                submitButton.setEnabled(false);
                resultTextView.setText("Tiempo terminado! Puntuación final: " + score);
                restartButton.setVisibility(View.VISIBLE);
            }
        }.start();
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

