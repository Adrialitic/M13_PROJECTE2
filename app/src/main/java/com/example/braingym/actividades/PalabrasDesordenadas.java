package com.example.braingym.actividades;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.braingym.Actividades;
import com.example.braingym.Musica.MusicaFondo;
import com.example.braingym.Musica.Gramola;
import com.example.braingym.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PalabrasDesordenadas extends AppCompatActivity {
    MusicaFondo musicaFondo = Gramola.getBackgroundMusic();
    private TextView tvScrambledWord, tvResult, tvScore, tvTimer;
    private EditText etUserInput;
    private Button btnCheck;
    private int score = 0;
    private int wordIndex = 0;
    private String[] words = {
            // Comida
            "manzana", "plátano", "sandía", "pizza", "hamburguesa",
            "pollo", "pasta", "ensalada", "sopa", "helado",
            "pan", "queso", "yogur", "arroz", "fresas",
            // Animales
            "perro", "gato", "pájaro", "pez", "elefante",
            "jirafa", "tigre", "león", "mono", "rinoceronte",
            "ballena", "tiburón", "pingüino", "cobra", "cocodrilo",
            // Objetos cotidianos
            "teléfono", "coche", "ordenador", "reloj", "televisión",
            "silla", "mesa", "cuchillo", "tenedor", "cuchara",
            "plato", "vaso", "libro", "bolígrafo", "cuaderno",
            // Prendas de ropa
            "camisa", "pantalón", "chaqueta", "vestido", "calcetines",
            "zapatos", "gorra", "bufanda", "guantes", "falda",
            "sombrero", "gafas", "corbata", "anillo", "collar",
            // Lugares
            "parque", "playa", "montaña", "ciudad", "bosque",
            "río", "lago", "isla", "desierto", "selva",
            "calle", "plaza", "iglesia", "castillo", "puente",
            // Muebles
            "sofá", "cama", "mesita", "armario", "estantería",
            "silla", "mesa", "escritorio", "taburete", "cómoda",
            "espejo", "perchero", "banqueta", "tumbona", "tocador",
            // Partes del cuerpo humano
            "cabeza", "brazo", "pierna", "mano", "dedo",
            "cuello", "hombro", "espalda", "rodilla", "pie",
            "tobillo", "muñeca", "codo", "uña", "cadera"
    };

    private String originalWord;
    private String scrambledWord;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabrasdesordenadas);

        tvScrambledWord = findViewById(R.id.WordTextView);
        etUserInput = findViewById(R.id.etUserInput);
        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);
        tvScore = findViewById(R.id.scoreTextView);
        tvTimer = findViewById(R.id.timerTextView);

        updateScore();
        startNewWord();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userInput = etUserInput.getText().toString().trim().toLowerCase();
                if (!userInput.isEmpty()) {
                    if (userInput.equals(originalWord)) {
                        score += 100;
                        tvResult.setText("¡Correcto!");
                        tvResult.setTextColor(Color.GREEN);
                    } else {
                        score -= 25;
                        tvResult.setText("Inténtalo de nuevo.");
                        tvResult.setTextColor(Color.RED);
                    }
                    tvResult.setVisibility(View.VISIBLE);
                    updateScore();
                    showAnimation(tvScrambledWord, userInput.equals(originalWord));
                    if (++wordIndex < 10) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startNewWord();
                            }
                        }, 1000);
                    } else {
                        endGame();
                    }
                } else {
                    Toast.makeText(PalabrasDesordenadas.this, "Por favor, ingrese una palabra", Toast.LENGTH_SHORT).show();
                }
            }
        });


        startTimer();
    }

    private void startNewWord() {
        originalWord = getRandomWord();
        scrambledWord = shuffleWord(originalWord);
        tvScrambledWord.setText(scrambledWord);
        etUserInput.setText("");
        tvResult.setVisibility(View.GONE);
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
        timer.cancel();
        tvResult.setText("Juego Terminado. Puntuación final: " + score);
        tvResult.setVisibility(View.VISIBLE);
        btnCheck.setEnabled(false);

        Button btnReturn = new Button(this);
        btnReturn.setText("Volver a Actividades");
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PalabrasDesordenadas.this, Actividades.class);
                startActivity(intent);
            }
        });
    }

    private String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    private String shuffleWord(String word) {
        List<Character> characters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        StringBuilder shuffledWord = new StringBuilder(word.length());
        while (!characters.isEmpty()) {
            int randPicker = (int) (Math.random() * characters.size());
            shuffledWord.append(characters.remove(randPicker));
        }
        return shuffledWord.toString();
    }

    private void showAnimation(final View view, boolean success) {
        final Drawable originalBackground = view.getBackground();
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(100);
        animation.setStartOffset(50);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(3);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (success) {
                    view.setBackgroundColor(Color.GREEN);
                } else {
                    view.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setBackground(originalBackground);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

}
