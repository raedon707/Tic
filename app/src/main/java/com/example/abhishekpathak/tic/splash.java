package com.example.abhishekpathak.tic;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class splash extends AppCompatActivity {

    public Button b1,b2;

    @Override
    public void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        ImageView img = (ImageView)findViewById(R.id.imageView);

        b2 = (Button)findViewById(R.id.b2);
        b1 = (Button)findViewById(R.id.b1);
        set_button(b2,"R");
        set_button(b1, "T");
        img.setBackgroundResource(R.drawable.king);
        timer();
    }

    private void set_button(Button b1, String s1) {
        b1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/pasajero.otf"));
        b1.setTextSize(80);
        b1.setText(s1);
    }

    public void timer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        },2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();

    }
}
