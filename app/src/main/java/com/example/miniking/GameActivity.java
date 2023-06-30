package com.example.miniking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        ImageButton yesButton = findViewById(R.id.yesButton);
        ImageButton noButton = findViewById(R.id.noButton);
        ImageButton helpButton = findViewById(R.id.helpButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        LinearLayout buttonsLayout =findViewById(R.id.buttonsLayout);
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
                    buttonsLayout.setVisibility(View.VISIBLE);
                }
                else {
                    settingsLayout.setVisibility(View.VISIBLE);
                    buttonsLayout.setVisibility(View.INVISIBLE);
                }
            }
        });


        TextView display = (TextView) findViewById(R.id.dialogTextView);
        new GameLoop(this, display, yesButton, noButton) {
        }.start();
    }
}