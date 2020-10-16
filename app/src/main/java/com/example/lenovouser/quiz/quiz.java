package com.example.lenovouser.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class quiz extends AppCompatActivity {
    private TextView mScoreView, mPhrase;
    private ImageView mImageView;
    private Button btnTrue, btnFalse;

    private boolean mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    public void initComponents() {
        mImageView = (ImageView)findViewById(R.id.imgPhoto);
        mScoreView = (TextView)findViewById(R.id.txtScore);
        mPhrase = (TextView)findViewById(R.id.txtPhrase);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        btnFalse = (Button)findViewById(R.id.btnFalse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        initComponents();

        updateQuestion();

        //Logica para el boton True
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAnswer == true) {
                    mScore++;// Actualiza la variable score
                    updateScore(mScore);

                    //Checa si actualiza la pregunta
                    if (mQuestionNumber == QuizCharge.frases.length) {
                        Intent i = new Intent(quiz.this, Results.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
                // Si la pregunta es incorrecta
                else {
                    if (mQuestionNumber == QuizCharge.frases.length) {
                        Intent i = new Intent(quiz.this, Results.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

        //Logica para el boton false
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAnswer == false) {
                    mScore++;
                    updateScore(mScore);

                    //perform check before you update the question
                    if (mQuestionNumber == QuizCharge.frases.length) {
                        Intent i = new Intent(quiz.this, Results.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
                else {
                    if (mQuestionNumber == QuizCharge.frases.length) {
                        Intent i = new Intent(quiz.this, Results.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        quiz.this.finish();
                        startActivity(i);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });
    }

    private void updateQuestion() {

        mImageView.setImageResource(QuizCharge.imagenes[mQuestionNumber]);
        mPhrase.setText(QuizCharge.frases[mQuestionNumber]);
        mAnswer = QuizCharge.respuestas[mQuestionNumber];
        mQuestionNumber++;
    }

    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

    public void clickExit(View view) {
        askToClose();
    }
    private void askToClose (){
        AlertDialog.Builder builder = new AlertDialog.Builder(quiz.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
