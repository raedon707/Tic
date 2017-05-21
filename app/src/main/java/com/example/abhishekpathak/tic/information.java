package com.example.abhishekpathak.tic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class information extends AppCompatActivity {

    TextView iyourScore, idrawGames, itotalamePlayed;
    int YOUR_SCORE,DRAW_GAMES,TOTAL_GAME_PLAYED;
    hard_mode hmode = new hard_mode();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.information);

        iyourScore = (TextView)findViewById(R.id.yourScore);
        idrawGames = (TextView)findViewById(R.id.drawGames);
        itotalamePlayed = (TextView)findViewById(R.id.totalGamePlayed);

        set_button(iyourScore, "Your Total Score : ");
        set_button(idrawGames, "Total Draw Games : ");
        set_button(itotalamePlayed, "Total Game Played : ");

        iyourScore.setText("Your Total Score : " +hmode.YOUR_SCORE_ANOTHER);
        idrawGames.setText("Total Draw Games : " +hmode.DRAW_SCORE_ANOTHER);
        itotalamePlayed.setText("Total Game Played : " +hmode.TOTAL_GAME_PLAYED_ANOTHER);
    }

    private void set_button(TextView b1, String s1) {
        b1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/pasajero.otf"));
        b1.setTextSize(20);
        b1.setText(s1);
    }
}
