package com.example.miniking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private static String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
    private static String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
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
        actionBar.hide();

        ImageButton yesButton = findViewById(R.id.yesButton);
        ImageButton noButton = findViewById(R.id.noButton);


        TextView display = (TextView) findViewById(R.id.dialogTextView);
        DrawTitle.go(display);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Asker a1 = new Asker("Press a Button to Begin.", false, display, yesButton, noButton);
                a1.run();
                display.setText("");
                Help h = new Help(display);
                a1 = new Asker("Press a Button to Continue.", false, display, yesButton, noButton);
                a1.run();
                Resources res = new Resources(display);
                Questions q = new Questions(res);
                MenuAsker menu = new MenuAsker(menuText, q, res, true, display);
            }
        }.start();

    }



}