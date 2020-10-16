package com.example.lenovouser.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    TextView mGrade, mFinalScore;
    Button mRetryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mRetryButton = (Button)findViewById(R.id.retry);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        mFinalScore.setText("Tu puntuación fue " + score + " de " + QuizCharge.frases.length);

        if (score >= 10){
            mGrade.setText("Excelente");
        }else if (score >= 8){
            mGrade.setText("Gran trabajo");
        }else if (score >= 7) {
            mGrade.setText("Buen esfuerzo");
        }else {
            mGrade.setText("Necesitas esforzarte más");
        }

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Results.this, QuizStart.class));
                Results.this.finish();
            }
        });
    }
}
