package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineexamination.SQLQuestions.DatabaseHandler2;
import com.example.onlineexamination.SQLQuestions.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DummyPage extends AppCompatActivity {

    Button save;
    TextView ques;
    CheckBox cb1, cb2, cb3, cb4;
    TextView quescount, countDownText;
    int countAdd = 0;
    String intentValue = "";

    int queValue = 1;
    int atteQue = 0;
    int correctAns = 0;

    List<Questions> listSelectedQue = new ArrayList<>();
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_page);

        intentValue = getIntent().getStringExtra("course");

        save = findViewById(R.id.save);
        ques = findViewById(R.id.ques);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        quescount = findViewById(R.id.quescount);
        countDownText = findViewById(R.id.countDownText);

        final DatabaseHandler2 databaseHandler = new DatabaseHandler2(this);
        List<Questions> listData = databaseHandler.getAllData();
        Collections.reverse(listData);

        System.out.println(listData);
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).getCoursekey() != null) {
                if (listData.get(i).getCoursekey().equals(intentValue)) {
                    listSelectedQue.add(listData.get(i));
                    System.out.println(listSelectedQue);
                }
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClickWorking();
            }
        });
        quescount.setText("Question:- " + queValue);

        String mQuestion = listSelectedQue.get(countAdd).getQuestion();

        ques.setText(mQuestion);
        cb1.setText(listSelectedQue.get(countAdd).getOptionA());
        cb2.setText(listSelectedQue.get(countAdd).getOptionB());
        cb3.setText(listSelectedQue.get(countAdd).getOptionC());
        cb4.setText(listSelectedQue.get(countAdd).getOptionD());

        countDown();
    }

    private void btnClickWorking() {
        String answerValue = "";

        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }

        if (cb1.isChecked() == true) {
            answerValue = cb1.getText().toString().trim();
        } else if (cb2.isChecked() == true) {
            answerValue = cb2.getText().toString().trim();
        } else if (cb3.isChecked() == true) {
            answerValue = cb3.getText().toString().trim();
        } else if (cb4.isChecked() == true) {
            answerValue = cb4.getText().toString().trim();
        }

        if (!answerValue.equals("")) {
            atteQue += 1;
        } else {
            System.out.println("not selected");
        }

        if (listSelectedQue!=null) {
            if (listSelectedQue.get(countAdd).getCorrectOption().equals(answerValue)) {
                System.out.println("Correct");
                correctAns += 1;
            } else {
                Toast.makeText(getApplicationContext(), "Correct ANS is:-  " + listSelectedQue.get(countAdd).getCorrectOption(), Toast.LENGTH_SHORT).show();
            }
        }

        countAdd += 1;
        queValue = countAdd + 1;
        quescount.setText("Question:- " + queValue);

        if (countAdd == 5) {
            Intent intent = new Intent(DummyPage.this, result.class);
            intent.putExtra("correctAns",correctAns);
            intent.putExtra("atteQue",atteQue);
            startActivity(intent);
            finish();

        } else {
            if (countAdd == 4) {
                save.setText("Show Result");
            }
            ques.setText("");

            if (cb1.isChecked() == true) {
                cb1.setChecked(false);
            } else if (cb2.isChecked() == true) {
                cb2.setChecked(false);
            } else if (cb3.isChecked() == true) {
                cb3.setChecked(false);
            } else if (cb4.isChecked() == true) {
                cb4.setChecked(false);
            }
            String mQuestion = listSelectedQue.get(countAdd).getQuestion();
            ques.setText(mQuestion);
            cb1.setText(listSelectedQue.get(countAdd).getOptionA());
            cb2.setText(listSelectedQue.get(countAdd).getOptionB());
            cb3.setText(listSelectedQue.get(countAdd).getOptionC());
            cb4.setText(listSelectedQue.get(countAdd).getOptionD());
        }
        countDown();
    }

    private void countDown() {
        countDownTimer = new CountDownTimer(60 * 1000, 1000) { // adjust the milli seconds here
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                countDownText.setText("Left Time : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                btnClickWorking();
            }
        }.start();
    }
}