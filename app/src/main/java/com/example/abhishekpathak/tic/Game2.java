package com.example.abhishekpathak.tic;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class Game2 extends AppCompatActivity{
    int c[][];
    int i = 0;
    int j = 0;
    public  Button[][] b;
    public int p1 = 0;
    public TextView yourScore, cpuScore, totalScore;
    public int YOUR_SOCRE=0, CPU_SCORE=0, TOTAL_SCORE=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.board);
        setBoard();
    }

    private void setBoard() {
        b = new Button[4][4];
        c = new int[4][4];

        yourScore = (TextView)findViewById(R.id.yourscore);
        cpuScore = (TextView)findViewById(R.id.cpuscore);
        totalScore = (TextView)findViewById(R.id.totalgame);

        b[1][1] = (Button)findViewById(R.id.button);
        b[1][2] = (Button)findViewById(R.id.button3);
        b[1][3] = (Button)findViewById(R.id.button4);
        b[2][1] = (Button)findViewById(R.id.button5);
        b[2][2] = (Button)findViewById(R.id.button6);
        b[2][3] = (Button)findViewById(R.id.button7);
        b[3][1] = (Button)findViewById(R.id.button8);
        b[3][2] = (Button)findViewById(R.id.button9);
        b[3][3] = (Button)findViewById(R.id.button10);


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
                b[i][j].setOnClickListener(new Game2.MyClickListener(i, j));
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
            if(view.getId() == R.id.reset) {
                resetB();
            }
            else if(p1 == 0) {
                p1T();
                checkBoard();
            }
            else if(p1 == 1) {
                p2T();
                checkBoard();
            }

        }

        public void p1T () {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setBackgroundResource(R.drawable.o);
                c[x][y] = 0;
                p1 = p1+1;
            }
        }

        public void p2T () {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setBackgroundResource(R.drawable.x);
                c[x][y] = 1;
                p1 = 0;
            }
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
            Toast t = Toast.makeText(getApplicationContext(), "Player 1 Won", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            YOUR_SOCRE++;
            yourScore.setText("Player 1: "+YOUR_SOCRE);
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    b[i][j].setEnabled(false);
                }
                gameOver = true;
            }
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            Toast t = Toast.makeText(getApplicationContext(),"Player 2 Won", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            CPU_SCORE++;
            cpuScore.setText("Player 2: "+CPU_SCORE);
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    b[i][j].setEnabled(false);
                }
                gameOver = true;
            }
        }

        else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                Toast t = Toast.makeText(getApplicationContext(),"draw",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                TOTAL_SCORE++;
                totalScore.setText("Draw: "+TOTAL_SCORE);
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
        setBoard();
    }
}