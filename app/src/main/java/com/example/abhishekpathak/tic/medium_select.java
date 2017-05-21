package com.example.abhishekpathak.tic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class medium_select extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.medium);

        Button easy = (Button)findViewById(R.id.easy);
        Button medium = (Button)findViewById(R.id.medium);
        Button hard = (Button)findViewById(R.id.hard);

        set_button(easy, "EASY");
        set_button(medium, "MEDIUM");
        set_button(hard, "EXTREME");

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(medium_select.this, Game.class);
                startActivity(i);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(medium_select.this, g.class);
                startActivity(i);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new  Intent(medium_select.this, hard_mode.class);
                startActivity(i);
            }
        });
    }

    private void set_button(Button b1, String s1) {
        b1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/pasajero.otf"));
        b1.setTextSize(40);
        b1.setText(s1);
    }
}