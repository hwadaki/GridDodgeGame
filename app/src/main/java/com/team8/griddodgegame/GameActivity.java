package com.team8.griddodgegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int[][] GridTotal = new int[8][8];

    int[][] GridUp = new int[8][8]; //위로 1
    int[][] GridRight = new int[8][8]; //오른쪽으로 2
    int[][] GridDown = new int[8][8]; //밑으로 3
    int[][] GridLeft = new int[8][8]; //왼쪽으로 4


    public int[][] gridPushing(int direction, int[][] gridArray){ //그리드 한칸 밀기
        Random rand = new Random();

        switch (direction){
            case 1: //위로
                for(int i=0;i<gridArray.length-1;i++){ //마지막줄 제외
                    for(int j=0;j<gridArray[0].length;j++){
                        gridArray[i][j] = gridArray[i+1][j];
                    }
                }
                Arrays.fill(gridArray[gridArray.length-1],0);
                break;

            case 2: //오른쪽으로
                for(int j=gridArray[0].length-1;j>0;j--){
                    for(int i=0; i<gridArray.length; i++){
                        gridArray[i][j] = gridArray[i-1][j];
                    }
                }
                for(int i=0; i<gridArray.length; i++){
                    gridArray[i][0] = 0;
                }
                break;

            case 3: //밑으로
                for(int i=gridArray.length-1;i>0;i--){ //마지막줄 제외
                    for(int j=0;j<gridArray[0].length;j++){
                        gridArray[i][j] = gridArray[i+1][j];
                    }
                }
                Arrays.fill(gridArray[gridArray.length-1],0);
                break;
        }

        return gridArray;
    };

    public boolean gridChange(int x, int y){ //그리드 병합 후, 닿았는 지 안 닿았는 지 여부 반환


        return false;
    };

    ImageButton upKey, leftKey, rightKey, downKey;
    TextView scoreText;

    int heartNum;

    int score = 0;
    int enemyDensity;
    int playerX, playerY;

    boolean damagedTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        upKey = findViewById(R.id.upKey);
        downKey = findViewById(R.id.downKey);
        leftKey = findViewById(R.id.leftKey);
        rightKey = findViewById(R.id.rightKey);
        scoreText = findViewById(R.id.scoreText);

        Arrays.fill(GridUp,0);
        Arrays.fill(GridDown,0);
        Arrays.fill(GridLeft,0);
        Arrays.fill(GridRight,0);
        Arrays.fill(GridTotal,0);

        Intent intent = getIntent();

        Integer gameLevel = intent.getIntExtra("gameLevel",0);
        switch(gameLevel){
            case 1:
                heartNum = 1;
                enemyDensity = 3;
                break;

            case 2:
                heartNum = 2;
                enemyDensity = 2;
                break;

            case 3:
                heartNum = 3;
                enemyDensity = 1;
                break;
        }

        playerX = 4; playerY = 4;

        upKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerY>=1){ //이동
                    playerY--;
                }

                damagedTF = gridChange(playerX, playerY); //폭탄 위치 갱신 후 닿았는 지 여부 확인

                //+xml 그리드 갱신

                if(damagedTF){ //닿았을 때
                    if(heartNum > 1){
                        heartNum--;
                        //+xml 하트 갯수 갱신
                        //+닿는 소리 재생
                    }
                    else{
                        //+게임오버 소리 재생

                        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                        intent.putExtra("score",score);

                        startActivity(intent);
                    }
                } else{ //안 닿았을 때

                    //+발걸음 소리 재생
                }

            }
        });

    }
}