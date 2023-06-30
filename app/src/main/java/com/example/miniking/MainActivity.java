package com.example.miniking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        ImageButton newGameButton = findViewById(R.id.newGameButton);
        ImageButton linkButton = findViewById(R.id.linkButton);
        ImageButton continueButton = findViewById(R.id.continueButton);
        ImageButton exitButton = findViewById(R.id.exitButton);
        ImageButton helpButton = findViewById(R.id.helpButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        LinearLayout leftButtonsLayout =findViewById(R.id.leftButtonsLayout);
        LinearLayout rightButtonsLayout =findViewById(R.id.rightButtonsLayout);
        FrameLayout settingsLayout = findViewById(R.id.settingsLayout);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the help view again
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open settings page
                if(settingsLayout.getVisibility() == View.VISIBLE) {
                    settingsLayout.setVisibility(View.INVISIBLE);
                    leftButtonsLayout.setVisibility(View.VISIBLE);
                    rightButtonsLayout.setVisibility(View.VISIBLE);
                }
                else {
                    settingsLayout.setVisibility(View.VISIBLE);
                    leftButtonsLayout.setVisibility(View.INVISIBLE);
                    rightButtonsLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start new game
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //continue game from save
            }
        });
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //link to my stuff
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //exit the game
            }
        });


        TextView display = (TextView) findViewById(R.id.dialogTextView);
        DrawTitle.go(display);
        DrawScene.close(display);
        Printer.printyBox("Welcome, and Enjoy!",display);
        DrawScene.close(display);


    }



}