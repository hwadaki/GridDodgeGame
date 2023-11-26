package com.team8.griddodgegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int[][] GridTotal = new int[8][8];

    int[][] GridUp = new int[8][8]; //위로 1
    int[][] GridRight = new int[8][8]; //오른쪽으로 2
    int[][] GridDown = new int[8][8]; //밑으로 3
    int[][] GridLeft = new int[8][8]; //왼쪽으로 4

    int[][] gridId = {
            {R.id.grid11,R.id.grid12,R.id.grid13,R.id.grid14,R.id.grid15,R.id.grid16,R.id.grid17,R.id.grid18},
            {R.id.grid21,R.id.grid22,R.id.grid23,R.id.grid24,R.id.grid25,R.id.grid26,R.id.grid27,R.id.grid28},
            {R.id.grid31,R.id.grid32,R.id.grid33,R.id.grid34,R.id.grid35,R.id.grid36,R.id.grid37,R.id.grid38},
            {R.id.grid41,R.id.grid42,R.id.grid43,R.id.grid44,R.id.grid45,R.id.grid46,R.id.grid47,R.id.grid48},
            {R.id.grid51,R.id.grid52,R.id.grid53,R.id.grid54,R.id.grid55,R.id.grid56,R.id.grid57,R.id.grid58},
            {R.id.grid61,R.id.grid62,R.id.grid63,R.id.grid64,R.id.grid65,R.id.grid66,R.id.grid67,R.id.grid68},
            {R.id.grid71,R.id.grid72,R.id.grid73,R.id.grid74,R.id.grid75,R.id.grid76,R.id.grid77,R.id.grid78},
            {R.id.grid81,R.id.grid82,R.id.grid83,R.id.grid84,R.id.grid85,R.id.grid86,R.id.grid87,R.id.grid88}};


    public void gridPushing(int direction, int[][] gridArray){ //그리드 한칸 밀기
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
                        gridArray[i][j] = gridArray[i][j-1];
                    }
                }
                for(int i=0; i<gridArray.length; i++){
                    gridArray[i][0] = 0;
                }
                break;

            case 3: //밑으로
                for(int i=gridArray.length-1;i>0;i--){ //마지막줄 제외
                    for(int j=0;j<gridArray[0].length;j++){
                        gridArray[i][j] = gridArray[i-1][j];
                    }
                }
                Arrays.fill(gridArray[0],0);
                break;

            case 4: //왼쪽으로
                for(int j=0;j<gridArray[0].length-1;j++){
                    for(int i=0; i<gridArray.length; i++){
                        gridArray[i][j] = gridArray[i][j+1];
                    }
                }
                for(int i=0; i<gridArray.length; i++){
                    gridArray[i][gridArray[0].length-1] = 0;
                }
                break;
        }
    };

    public void gridRandomInsert(int n){
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int randomDirection = rand.nextInt(3)+1;
            int randomIndex = rand.nextInt(7);

            switch(randomDirection){
                case 1:
                    GridUp[GridUp.length-1][randomIndex] = 1;
                    break;
                case 2:
                    GridRight[randomIndex][0] = 2;
                    break;
                case 3:
                    GridDown[0][randomIndex] = 4;
                    break;
                case 4:
                    GridLeft[randomIndex][GridLeft.length-1] = 8;
                    break;
            }
        }
    };

    public int countEnemy(int[][] array2d){
        int enemy=0;

        for(int i=0;i<array2d.length;i++){ //마지막줄 제외
            for(int j=0;j<array2d[0].length;j++){
                if(array2d[i][j] != 0 && array2d[i][j] != -1){
                    enemy++;
                }
            }
        }

        return enemy;
    };


    public boolean gridMerge(int x, int y, int playerDirection){ //그리드 병합 후, 닿았는 지 안 닿았는 지 여부 반환
        boolean touchedTF = false;

        if(GridTotal[x][y] != 0){
            touchedTF = true;
            GridUp[x][y] = 0;
            GridRight[x][y] = 0;
            GridDown[x][y] = 0;
            GridLeft[x][y] = 0;
        }

        gridPushing(1,GridUp);
        gridPushing(2,GridRight);
        gridPushing(3,GridDown);
        gridPushing(4,GridLeft);

        gridRandomInsert(enemyDensity);

        for(int j=0;j<GridTotal[0].length;j++){
            for(int i=0; i<GridTotal.length; i++){
                GridTotal[i][j] = (GridUp[i][j]+GridRight[i][j]+GridDown[i][j]+GridLeft[i][j]);

                if(!(GridTotal[i][j] == 0 || GridTotal[i][j] == 1 || GridTotal[i][j] == 2 || GridTotal[i][j] == 4 || GridTotal[i][j] == 8)){
                    GridUp[i][j] = 0;
                    GridRight[i][j] = 0;
                    GridDown[i][j] = 0;
                    GridLeft[i][j] = 0;

                    GridTotal[i][j] = -1;
                }
            }
        }

        for(int i2=0;i2<GridTotal.length;i2++){ //출력용
            for(int j2=0;j2<GridTotal[0].length;j2++){
                System.out.print(GridTotal[i2][j2]+" ");
            }
            System.out.println();
        }

        System.out.println(x+","+y+": "+GridTotal[x][y]);

        if(GridTotal[x][y] == 0 && !touchedTF)
            return false;
        else {
            GridUp[x][y] = 0;
            GridRight[x][y] = 0;
            GridDown[x][y] = 0;
            GridLeft[x][y] = 0;
            return true;
        }
    };

    ImageButton upKey, leftKey, rightKey, downKey;
    ImageView heart1, heart2, heart3;
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

        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        scoreText = findViewById(R.id.scoreText);

        for(int i=0;i<GridTotal.length;i++){
            Arrays.fill(GridUp[0],0);
            Arrays.fill(GridDown[0],0);
            Arrays.fill(GridLeft[0],0);
            Arrays.fill(GridRight[0],0);
            Arrays.fill(GridTotal[0],0);
        }

        Intent intent = getIntent();

        int gameLevel = intent.getIntExtra("gameLevel",0);
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
        switch(heartNum){
            case 1:
                heart1.setImageResource(R.drawable.heart);
                heart2.setImageResource(R.drawable.heart_none);
                heart3.setImageResource(R.drawable.heart_none);
                break;
            case 2:
                heart1.setImageResource(R.drawable.heart);
                heart2.setImageResource(R.drawable.heart);
                heart3.setImageResource(R.drawable.heart_none);
                break;
            case 3:
                heart1.setImageResource(R.drawable.heart);
                heart2.setImageResource(R.drawable.heart);
                heart3.setImageResource(R.drawable.heart);
                break;
            default:
                break;
        }

        playerX = 4; playerY = 4;

        damagedTF = gridMerge(playerX, playerY,-1); //폭탄 위치 갱신 후 닿았는 지 여부 확인

        for(int i=0;i<GridTotal.length;i++){
            for(int j=0;j<GridTotal[0].length;j++){
                ImageButton gridButton = findViewById(gridId[i][j]);
                switch(GridTotal[i][j]){
                    case 0:
                        gridButton.setImageResource(0);
                        break;
                    case 1: //up 0001
                        gridButton.setImageResource(R.drawable.bombup);
                        break;
                    case 2: //right 0010
                        gridButton.setImageResource(R.drawable.bombright);
                        break;
                    case 4: //down 0100
                        gridButton.setImageResource(R.drawable.bombdown);
                        break;
                    case 8: //left 1000
                        gridButton.setImageResource(R.drawable.bombleft);
                        break;
                    case -1: //boom
                        gridButton.setImageResource(R.drawable.boom);
                        break;
                    default:
                        break;
                }

                if(playerX==i && playerY==j){
                    gridButton.setImageResource(R.drawable.player);
                }
            }
        }

        upKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerX>=1){ //이동
                    playerX--;
                }

                damagedTF = gridMerge(playerX, playerY,1); //폭탄 위치 갱신 후 닿았는 지 여부 확인
                System.out.println(damagedTF);

                for(int i=0;i<GridTotal.length;i++){
                    for(int j=0;j<GridTotal[0].length;j++){
                        ImageButton gridButton = findViewById(gridId[i][j]);
                        switch(GridTotal[i][j]){
                            case 0:
                                gridButton.setImageResource(0);
                                break;
                            case 1: //up 0001
                                gridButton.setImageResource(R.drawable.bombup);
                                break;
                            case 2: //right 0010
                                gridButton.setImageResource(R.drawable.bombright);
                                break;
                            case 4: //down 0100
                                gridButton.setImageResource(R.drawable.bombdown);
                                break;
                            case 8: //left 1000
                                gridButton.setImageResource(R.drawable.bombleft);
                                break;
                            case -1: //boom
                                gridButton.setImageResource(R.drawable.boom);
                                break;
                            default:
                                break;
                        }

                        if(playerX==i && playerY==j){
                            if(damagedTF)
                                gridButton.setImageResource(R.drawable.player_damaged);
                            else
                                gridButton.setImageResource(R.drawable.player);
                        }
                    }
                }

                if(damagedTF){ //닿았을 때
                    if(heartNum > 1){
                        heartNum--;

                        switch(heartNum){
                            case 1:
                                heart2.setImageResource(R.drawable.heart_none);
                                break;
                            case 2:
                                heart3.setImageResource(R.drawable.heart_none);
                                break;
                        }
                        //+닿는 소리 재생
                    }
                    else{
                        heart1.setImageResource(R.drawable.heart_none);
                        //+게임오버 소리 재생

                        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                        intent.putExtra("score",score);

                        startActivity(intent);
                    }
                    System.out.println(heartNum);
                } else{ //안 닿았을 때
                    score += countEnemy(GridTotal);
                    scoreText.setText("점수 : "+score);
                    //+발걸음 소리 재생
                }

            }
        });

        rightKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerY<GridTotal.length-1){ //이동
                    playerY++;
                }

                damagedTF = gridMerge(playerX, playerY,2); //폭탄 위치 갱신 후 닿았는 지 여부 확인
                System.out.println(damagedTF);

                for(int i=0;i<GridTotal.length;i++){
                    for(int j=0;j<GridTotal[0].length;j++){
                        ImageButton gridButton = findViewById(gridId[i][j]);
                        switch(GridTotal[i][j]){
                            case 0:
                                gridButton.setImageResource(0);
                                break;
                            case 1: //up 0001
                                gridButton.setImageResource(R.drawable.bombup);
                                break;
                            case 2: //right 0010
                                gridButton.setImageResource(R.drawable.bombright);
                                break;
                            case 4: //down 0100
                                gridButton.setImageResource(R.drawable.bombdown);
                                break;
                            case 8: //left 1000
                                gridButton.setImageResource(R.drawable.bombleft);
                                break;
                            case -1: //boom
                                gridButton.setImageResource(R.drawable.boom);
                                break;
                            default:
                                break;
                        }

                        if(playerX==i && playerY==j){
                            if(damagedTF)
                                gridButton.setImageResource(R.drawable.player_damaged);
                            else
                                gridButton.setImageResource(R.drawable.player);
                        }
                    }
                }

                if(damagedTF){ //닿았을 때
                    if(heartNum > 1){
                        heartNum--;

                        switch(heartNum){
                            case 1:
                                heart2.setImageResource(R.drawable.heart_none);
                                break;
                            case 2:
                                heart3.setImageResource(R.drawable.heart_none);
                                break;
                        }
                        //+닿는 소리 재생
                    }
                    else{
                        heart1.setImageResource(R.drawable.heart_none);
                        //+게임오버 소리 재생

                        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                        intent.putExtra("score",score);

                        startActivity(intent);
                    }
                    System.out.println(heartNum);
                } else{ //안 닿았을 때
                    score += countEnemy(GridTotal);
                    scoreText.setText("점수 : "+score);
                    //+발걸음 소리 재생
                }

            }
        });

        downKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerX<GridTotal.length-1){ //이동
                    playerX++;
                }

                damagedTF = gridMerge(playerX, playerY,3); //폭탄 위치 갱신 후 닿았는 지 여부 확인
                System.out.println(damagedTF);

                for(int i=0;i<GridTotal.length;i++){
                    for(int j=0;j<GridTotal[0].length;j++){
                        ImageButton gridButton = findViewById(gridId[i][j]);
                        switch(GridTotal[i][j]){
                            case 0:
                                gridButton.setImageResource(0);
                                break;
                            case 1: //up 0001
                                gridButton.setImageResource(R.drawable.bombup);
                                break;
                            case 2: //right 0010
                                gridButton.setImageResource(R.drawable.bombright);
                                break;
                            case 4: //down 0100
                                gridButton.setImageResource(R.drawable.bombdown);
                                break;
                            case 8: //left 1000
                                gridButton.setImageResource(R.drawable.bombleft);
                                break;
                            case -1: //boom
                                gridButton.setImageResource(R.drawable.boom);
                                break;
                            default:
                                break;
                        }

                        if(playerX==i && playerY==j){
                            if(damagedTF)
                                gridButton.setImageResource(R.drawable.player_damaged);
                            else
                                gridButton.setImageResource(R.drawable.player);
                        }
                    }
                }

                if(damagedTF){ //닿았을 때
                    if(heartNum > 1){
                        heartNum--;

                        switch(heartNum){
                            case 1:
                                heart2.setImageResource(R.drawable.heart_none);
                                break;
                            case 2:
                                heart3.setImageResource(R.drawable.heart_none);
                                break;
                        }
                        //+닿는 소리 재생
                    }
                    else{
                        heart1.setImageResource(R.drawable.heart_none);
                        //+게임오버 소리 재생

                        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                        intent.putExtra("score",score);

                        startActivity(intent);
                    }
                    System.out.println(heartNum);
                } else{ //안 닿았을 때
                    score += countEnemy(GridTotal);
                    scoreText.setText("점수 : "+score);
                    //+발걸음 소리 재생
                }

            }
        });

        leftKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerY>0){ //이동
                    playerY--;
                }

                damagedTF = gridMerge(playerX, playerY,4); //폭탄 위치 갱신 후 닿았는 지 여부 확인
                System.out.println(damagedTF);

                for(int i=0;i<GridTotal.length;i++){
                    for(int j=0;j<GridTotal[0].length;j++){
                        ImageButton gridButton = findViewById(gridId[i][j]);
                        switch(GridTotal[i][j]){
                            case 0:
                                gridButton.setImageResource(0);
                                break;
                            case 1: //up 0001
                                gridButton.setImageResource(R.drawable.bombup);
                                break;
                            case 2: //right 0010
                                gridButton.setImageResource(R.drawable.bombright);
                                break;
                            case 4: //down 0100
                                gridButton.setImageResource(R.drawable.bombdown);
                                break;
                            case 8: //left 1000
                                gridButton.setImageResource(R.drawable.bombleft);
                                break;
                            case -1: //boom
                                gridButton.setImageResource(R.drawable.boom);
                                break;
                            default:
                                break;
                        }

                        if(playerX==i && playerY==j){
                            if(damagedTF)
                                gridButton.setImageResource(R.drawable.player_damaged);
                            else
                                gridButton.setImageResource(R.drawable.player);
                        }
                    }
                }

                if(damagedTF){ //닿았을 때
                    if(heartNum > 1){
                        heartNum--;

                        switch(heartNum){
                            case 1:
                                heart2.setImageResource(R.drawable.heart_none);
                                break;
                            case 2:
                                heart3.setImageResource(R.drawable.heart_none);
                                break;
                        }
                        //+닿는 소리 재생
                    }
                    else{
                        heart1.setImageResource(R.drawable.heart_none);
                        //+게임오버 소리 재생

                        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                        intent.putExtra("score",score);

                        startActivity(intent);
                    }
                    System.out.println(heartNum);
                } else{ //안 닿았을 때
                    score += countEnemy(GridTotal);
                    scoreText.setText("점수 : "+score);
                    //+발걸음 소리 재생
                }

            }
        });

    }
}