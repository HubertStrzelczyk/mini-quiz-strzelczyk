package com.example.miniquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private Button btnStart, btnReset, btnA, btnB, btnC;
    private List<Question> allQuestions;
    private List<Question> quizQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnA = findViewById(R.id.btnOptionA);
        btnB = findViewById(R.id.btnOptionB);
        btnC = findViewById(R.id.btnOptionC);

        allQuestions = new ArrayList<>();
        allQuestions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        allQuestions.add(new Question("Największa planeta?", "Ziemia", "Jowisz", "Mars", "Jowisz"));
        allQuestions.add(new Question("2 + 2 * 2 wynosi:", "8", "4", "6", "6"));
        allQuestions.add(new Question("Kolor trawy to zazwyczaj:", "Niebieski", "Zielony", "Czerwony", "Zielony"));
        allQuestions.add(new Question("Symbol wody to:", "H2O", "CO2", "O2", "H2O"));
        allQuestions.add(new Question("Stolica Polski to:", "Kraków", "Warszawa", "Gdańsk", "Warszawa"));

        btnStart.setOnClickListener(v -> startQuiz());
        btnReset.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerListener = v -> {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().toString().equals(quizQuestions.get(currentQuestionIndex).getCorrectAnswer())) {
                score++;
                tvScore.setText("Wynik: " + score);
            }
            currentQuestionIndex++;
            showQuestion();
        };

        btnA.setOnClickListener(answerListener);
        btnB.setOnClickListener(answerListener);
        btnC.setOnClickListener(answerListener);
    }

    private void startQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Wynik: 0");
        Collections.shuffle(allQuestions);
        quizQuestions = new ArrayList<>(allQuestions.subList(0, 5));
        btnStart.setVisibility(View.GONE);
        toggleQuizUI(View.VISIBLE);
        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestionIndex < 5) {
            Question q = quizQuestions.get(currentQuestionIndex);
            tvQuestion.setText(q.getQuestionText());
            btnA.setText(q.getOptionA());
            btnB.setText(q.getOptionB());
            btnC.setText(q.getOptionC());
        } else {
            toggleQuizUI(View.GONE);
            Toast.makeText(this, "Koniec quizu! Twój wynik: " + score + " / 5", Toast.LENGTH_LONG).show();
        }
    }

    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Wynik: 0");
        toggleQuizUI(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
    }

    private void toggleQuizUI(int visibility) {
        tvQuestion.setVisibility(visibility);
        btnA.setVisibility(visibility);
        btnB.setVisibility(visibility);
        btnC.setVisibility(visibility);
    }
}