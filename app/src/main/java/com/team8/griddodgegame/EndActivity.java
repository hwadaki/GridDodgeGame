package com.team8.griddodgegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {

    ImageButton restartBtn;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        restartBtn = findViewById(R.id.restartBtn);
        scoreText = findViewById(R.id.scoreText);


        Intent intent = getIntent();

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endintent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(endintent);
            }
        });


    }
}