package com.team8.griddodgegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Button StartBtn;
    Spinner spinnerLevel;

    int gameLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartBtn = findViewById(R.id.startBtn);
        StartBtn.setBackgroundColor(Color.RED);
        spinnerLevel = findViewById(R.id.selectLevel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.level,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    gameLevel = 1;
                } else if(i==1){
                    gameLevel = 2;
                } else if(i==2){
                    gameLevel = 3;
                }
                System.out.println(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gameLevel = 1;
                System.out.println("gamelevel select error");
            }

        });

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("gameLevel",gameLevel);

                startActivity(intent);
            }
        });
    }
}