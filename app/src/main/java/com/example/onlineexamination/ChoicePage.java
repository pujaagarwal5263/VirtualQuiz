package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChoicePage extends AppCompatActivity {
Button course;
Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_page);

         course = findViewById(R.id.course);

         log = findViewById(R.id.log);

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChoicePage.this, SelectCourse.class);
                startActivity(intent);

            }
        });




                log.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //   Toast.makeText(getApplicationContext(),"Enter the valid username",Toast.LENGTH_SHORT).show();
                        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(ChoicePage.this).create();
                        alertDialog.setTitle("logout");
                        alertDialog.setMessage(" Are you sure you want to logout ? ");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        SharedPreferences.Editor editor = getSharedPreferences("myPre", MODE_PRIVATE).edit();
                                        editor.putBoolean("loginTrue",false);
                                        editor.apply();
                                        Intent cold = new Intent(ChoicePage.this, MainActivity.class);
                                        startActivity(cold);
                                        finish();
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();
                    }
                });
            }

    }


