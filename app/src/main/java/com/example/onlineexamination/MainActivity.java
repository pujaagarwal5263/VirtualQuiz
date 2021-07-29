package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button user;
    Button admin;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        user = findViewById(R.id.user);
        admin = findViewById(R.id.admin);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("myPre2", MODE_PRIVATE);
                boolean restoredText = prefs.getBoolean("login1True", false);

                if (restoredText == true) {
                    Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(MainActivity.this, FrontPage.class);
                    intent.putExtra("user", "User");
                    startActivity(intent);
                }
            }
        });


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences("myPre", MODE_PRIVATE);
                boolean restoredText = prefs.getBoolean("loginTrue", false);

                if (restoredText == true) {
                    Intent intent = new Intent(MainActivity.this, ChoicePage.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(MainActivity.this, AdminLogin.class);
                    intent.putExtra("user", "Admin");
                    startActivity(intent);
                }
                // Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

            }
        });

    }

}



