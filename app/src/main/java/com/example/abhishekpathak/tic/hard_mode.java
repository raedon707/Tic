package com.example.abhishekpathak.tic;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class hard_mode extends AppCompatActivity {
    int c[][];
    int i = 0;
    int j = 0;
    public Button[][] b;
    public Button reset;
    public TextView yourScore, cpuScore, totalScore;
    public int YOUR_SCORE=0, CPU_SCORE=0, DRAW_SCORE=0, TOTAL_GAME_PLAYED=0;
    public int YOUR_SCORE_ANOTHER=0,DRAW_SCORE_ANOTHER=0,TOTAL_GAME_PLAYED_ANOTHER=0;
    comp pc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.board);
        yourScore = (TextView)findViewById(R.id.yourscore);
        cpuScore = (TextView)findViewById(R.id.cpuscore);
        totalScore = (TextView)findViewById(R.id.totalgame);
       LOL();
    }

    private void LOL() {
        setBoard();
        pc.Turn();
    }

    private void setBoard() {
        pc = new comp();
        b = new Button[4][4];
        c = new int[4][4];

        b[0][0] = (Button)findViewById(R.id.reset);
        b[1][1] = (Button) findViewById(R.id.button);
        b[1][2] = (Button) findViewById(R.id.button3);
        b[1][3] = (Button) findViewById(R.id.button4);
        b[2][1] = (Button) findViewById(R.id.button5);
        b[2][2] = (Button) findViewById(R.id.button6);
        b[2][3] = (Button) findViewById(R.id.button7);
        b[3][1] = (Button) findViewById(R.id.button8);
        b[3][2] = (Button) findViewById(R.id.button9);
        b[3][3] = (Button) findViewById(R.id.button10);
        reset = (Button)findViewById(R.id.reset);

        Display disp = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        int width = size.x;
        int height = size.y;

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width/3-10,height/5-10);
                if (i == 1) {
                    params.setMargins(0 + (width / 3) * (j - 1), 0, 0, 0);
                    b[i][j].setLayoutParams(params);
                }
                if (i == 2) {
                    params.setMargins(0 + (width / 3) * (j - 1), height/5, 0, 0);
                    b[i][j].setLayoutParams(params);
                }
                if (i == 3) {
                    params.setMargins(0 + (width/3) * (j-1), (height/5) * 2, 0, 0);
                    b[i][j].setLayoutParams(params);
                }
                b[i][j].setOnClickListener(new hard_mode.MyClickListener(i, j));
                if (!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    class MyClickListener implements View.OnClickListener {
        int x;
        int y;
        Button reset = (Button)findViewById(R.id.reset);

        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
            reset.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/pasajero.otf"));
            reset.setTextSize(40);
            reset.setText("RESET");
            reset.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setBackgroundResource(R.drawable.o);
                c[x][y] = 0;
                if (!checkBoard()) {
                    timer();
                    pc.Turn();
                }
            }
            if(view.getId() == R.id.reset) {
                resetB();
            }
        }

    }
    
    private class comp {
        public void Turn() {
            if (c[1][1] == 2 &&
                    ((c[1][2] == 1 && c[1][3] == 1) ||
                            (c[2][2] == 1 && c[3][3] == 1) ||
                            (c[2][1] == 1 && c[3][1] == 1))) {
                markSquare(1, 1);
            } else if (c[1][2] == 2 &&
                    ((c[2][2] == 1 && c[3][2] == 1) ||
                            (c[1][1] == 1 && c[1][3] == 1))) {
                markSquare(1, 2);
            } else if (c[1][3] == 2 &&
                    ((c[1][1] == 1 && c[1][2] == 1) ||
                            (c[3][1] == 1 && c[2][2] == 1) ||
                            (c[2][3] == 1 && c[3][3] == 1))) {
                markSquare(1, 3);
            } else if (c[2][1] == 2 &&
                    ((c[2][2] == 1 && c[2][3] == 1) ||
                            (c[1][1] == 1 && c[3][1] == 1))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[1][1] == 1 && c[3][3] == 1) ||
                            (c[1][2] == 1 && c[3][2] == 1) ||
                            (c[3][1] == 1 && c[1][3] == 1) ||
                            (c[2][1] == 1 && c[2][3] == 1))) {
                markSquare(2, 2);
            } else if (c[2][3] == 2 &&
                    ((c[2][1] == 1 && c[2][2] == 1) ||
                            (c[1][3] == 1 && c[3][3] == 1))) {
                markSquare(2, 3);
            } else if (c[3][1] == 2 &&
                    ((c[1][1] == 1 && c[2][1] == 1) ||
                            (c[3][2] == 1 && c[3][3] == 1) ||
                            (c[2][2] == 1 && c[1][3] == 1))) {
                markSquare(3, 1);
            } else if (c[3][2] == 2 &&
                    ((c[1][2] == 1 && c[2][2] == 1) ||
                            (c[3][1] == 1 && c[3][3] == 1))) {
                markSquare(3, 2);
            } else if (c[3][3] == 2 &&
                    ((c[1][1] == 1 && c[2][2] == 1) ||
                            (c[1][3] == 1 && c[2][3] == 1) ||
                            (c[3][1] == 1 && c[3][2] == 1))) {
                markSquare(3, 3);
            } else if (c[1][1] == 2 &&
                    ((c[1][2] == 0 && c[1][3] == 0) ||
                            (c[2][2] == 0 && c[3][3] == 0) ||
                            (c[2][1] == 0 && c[3][1] == 0))) {
                markSquare(1, 1);
            } else if (c[1][2] == 2 &&
                    ((c[2][2] == 0 && c[3][2] == 0) ||
                            (c[1][1] == 0 && c[1][3] == 0))) {
                markSquare(1, 2);
            } else if (c[1][3] == 2 &&
                    ((c[1][1] == 0 && c[1][2] == 0) ||
                            (c[3][1] == 0 && c[2][2] == 0) ||
                            (c[2][3] == 0 && c[3][3] == 0))) {
                markSquare(1, 3);
            } else if (c[2][1] == 2 &&
                    ((c[2][2] == 0 && c[2][3] == 0) ||
                            (c[1][1] == 0 && c[3][1] == 0))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[1][1] == 0 && c[3][3] == 0) ||
                            (c[1][2] == 0 && c[3][2] == 0) ||
                            (c[3][1] == 0 && c[1][3] == 0) ||
                            (c[2][1] == 0 && c[2][3] == 0))) {
                markSquare(2, 2);
            } else if (c[2][3] == 2 &&
                    ((c[2][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[3][3] == 0))) {
                markSquare(2, 3);
            } else if (c[3][1] == 2 &&
                    ((c[1][1] == 0 && c[2][1] == 0) ||
                            (c[3][2] == 0 && c[3][3] == 0) ||
                            (c[2][2] == 0 && c[1][3] == 0))) {
                markSquare(3, 1);
            } else if (c[3][2] == 2 &&
                    ((c[1][2] == 0 && c[2][2] == 0) ||
                            (c[3][1] == 0 && c[3][3] == 0))) {
                markSquare(3, 2);
            } else if (c[3][3] == 2 &&
                    ((c[1][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[2][3] == 0) ||
                            (c[3][1] == 0 && c[3][2] == 0))) {
                markSquare(3, 3);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(4);
                int b = rand.nextInt(4);
                while (a == 0 || b == 0 || c[a][b] != 2) {
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                }
                markSquare(a, b);
            }
        }

        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            b[x][y].setBackgroundResource(R.drawable.x);
            c[x][y] = 1;
            checkBoard();
        }
    }

    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            Toast t = Toast.makeText(getApplicationContext(), "Congratulations, You Win!", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            YOUR_SCORE++;
            TOTAL_GAME_PLAYED++;
            this.YOUR_SCORE_ANOTHER = YOUR_SCORE;
            this.TOTAL_GAME_PLAYED_ANOTHER = TOTAL_GAME_PLAYED;
            yourScore.setText("Your Score: "+YOUR_SCORE);
            timer();
            resetB();
            gameOver = true;
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            Toast t = Toast.makeText(getApplicationContext(), "Looser!", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            CPU_SCORE++;
            TOTAL_GAME_PLAYED++;
            this.TOTAL_GAME_PLAYED_ANOTHER = TOTAL_GAME_PLAYED;
            cpuScore.setText("CPU Score: "+CPU_SCORE);
            timer();
            resetB();
            gameOver = true;

        } else {
            boolean empty = false;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    if (c[i][j] == 2) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                gameOver = true;
                Toast t = Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                DRAW_SCORE++;
                this.DRAW_SCORE_ANOTHER = DRAW_SCORE;
                TOTAL_GAME_PLAYED++;
                this.TOTAL_GAME_PLAYED_ANOTHER = TOTAL_GAME_PLAYED;
                totalScore.setText("Draw: "+DRAW_SCORE);
                timer();
                resetB();
            }
        }
        return gameOver;
    }

    public void resetB() {
        for(int i=1;i<4;i++) {
            for(int j=1;j<4;j++) {
                b[i][j].setEnabled(true);
                b[i][j].setBackgroundResource(R.drawable.shadow);
            }
        }
        LOL();
    }

    public void timer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },2000);
    }
}





