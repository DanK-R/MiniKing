package com.example.miniking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    GameActivity gameActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameActivity = GameActivity.this;

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

        ImageButton yesDutyButton = findViewById(R.id.yesDutyButton);
        ImageButton noExitButton = findViewById(R.id.noExitButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);
        ImageButton mapButton = findViewById(R.id.mapButton);
        ImageButton helpButton = findViewById(R.id.helpButton);
        FrameLayout settingsLayout = findViewById(R.id.settingsLayout);

        settingsButton.setOnClickListener(v -> {
            //open settings page
            if(settingsLayout.getVisibility() == View.VISIBLE) {

                yesDutyButton.setEnabled(true);
                noExitButton.setEnabled(true);
                if(mapButton.getVisibility() == View.VISIBLE) {
                    mapButton.setEnabled(true);
                    helpButton.setEnabled(true);
                }
                settingsLayout.setVisibility(View.INVISIBLE);
            }
            else {
                yesDutyButton.setEnabled(false);
                noExitButton.setEnabled(false);
                if(mapButton.getVisibility() == View.VISIBLE) {
                    mapButton.setEnabled(false);
                    helpButton.setEnabled(false);
                }
                settingsLayout.setVisibility(View.VISIBLE);
            }
        });

        TextView display = findViewById(R.id.dialogTextView);
        new GameLoop(this, display, yesDutyButton, noExitButton, mapButton, helpButton, gameActivity){
        }.start();
    }
}