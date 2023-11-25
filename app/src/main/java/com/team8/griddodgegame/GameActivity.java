package com.team8.griddodgegame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    ImageButton upKey, leftKey, rightKey, downKey;
    TextView scoreText;

    int heartNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        upKey = findViewById(R.id.upKey);
        downKey = findViewById(R.id.downKey);
        leftKey = findViewById(R.id.leftKey);
        rightKey = findViewById(R.id.rightKey);
        scoreText = findViewById(R.id.scoreText);

        Intent intent = getIntent();

        Integer gameLevel = intent.getIntExtra("gameLevel",0);

        int[][] GridTotal = new int[10][10];
    }
}