package com.example.abhishekpathak.tic;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.front);

        //declaration
        Toolbar toolbar = (Toolbar)findViewById(R.id.action);
        Button play = (Button) findViewById(R.id.play);
        Button contact = (Button) findViewById(R.id.contact);
        Button help = (Button) findViewById(R.id.help);
        FloatingActionButton share = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mDrawer = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_closed, R.string.drawer_closed);

        //setting the values
        setSupportActionBar(toolbar);
        set_button(play, "PLAY");
        set_button(contact, "SCORE");
        set_button(help, "HELP");
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Switch s = (Switch) findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "2 Player Mode Is Selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "1 Player Mode Is Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //play

        //dialogue box declaration.
        final CharSequence type[] = new CharSequence[]{"Online", "Offline"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mode Select");

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s.isChecked()) {
                    builder.setItems(type, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 1) {
                                Intent i = new Intent(getApplicationContext(), Game2.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Right now you can't play in this mode, this part of the application is under development", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.show();
                } else {
                    Intent i = new Intent(getApplicationContext(), medium_select.class);
                    startActivity(i);
                }
            }
        });

        //contact
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), information.class);
                startActivity(i);
            }
        });

        //help
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), help.class);
                startActivity(i);
            }
        });

        //share
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                startActivity(Intent.createChooser(i, "Share Via"));
            }
        });
    }

    //fonts for buttons
    private void set_button(Button b1, String s1) {
        b1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/pasajero.otf"));
        b1.setTextSize(40);
        b1.setText(s1);
    }

    //exit on double press
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}