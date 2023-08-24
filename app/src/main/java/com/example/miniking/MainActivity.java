package com.example.miniking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private ImageButton newGameButton;
    private ImageButton linkButton;
    private ImageButton continueButton;
    private ImageButton exitButton;
    private ImageButton settingsButton;
    private ImageButton saveButton1;
    private ImageButton saveButton2;
    private ImageButton saveButton3;
    private ImageButton saveExitButton;
    private FrameLayout savesLayout;
    private FrameLayout settingsLayout;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
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




        newGameButton = findViewById(R.id.newGameButton);
        linkButton = findViewById(R.id.linkButton);
        continueButton = findViewById(R.id.continueButton);
        exitButton = findViewById(R.id.exitButton);
        settingsButton = findViewById(R.id.settingsButtonMain);
        saveButton1 = findViewById(R.id.saveButton1);
        saveButton2 = findViewById(R.id.saveButton2);
        saveButton3 = findViewById(R.id.saveButton3);
        savesLayout = findViewById(R.id.savesLayout);
        saveExitButton = findViewById(R.id.saveExitButton);
        settingsLayout = findViewById(R.id.settingsLayout);

        TextView save1TextView = findViewById(R.id.save1TextView);
        TextView save2TextView = findViewById(R.id.save2TextView);
        TextView save3TextView = findViewById(R.id.save3TextView);

        SaveDataParser sdp1 = new SaveDataParser(this, null, null, "save1");
        SaveDataParser sdp2 = new SaveDataParser(this, null, null, "save2");
        SaveDataParser sdp3 = new SaveDataParser(this, null, null, "save3");

        String date1;
        if(sdp1.getValidity()) {
            date1 = sdp1.getDate();
        }
        else {
            date1 = "EMPTY";
        }
        String date2;
        if(sdp2.getValidity()) {
             date2 = sdp2.getDate();
        }
        else {
            date2 = "EMPTY";
        }
        String date3;
        if(sdp3.getValidity()) {
            date3 = sdp3.getDate();
        }
        else {
            date3 = "EMPTY";
        }

        save1TextView.setText(date1);
        save2TextView.setText(date2);
        save3TextView.setText(date3);


        buttonListenerSetup();

        TextView display = (TextView) findViewById(R.id.dialogTextView);




        DrawTitle.go(display);
        DrawScene.close(display);
        Printer.printyBox("Welcome, and Enjoy!",display);
        DrawScene.close(display);


    }

    private void buttonListenerSetup() {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start new game

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        newGameButton.setImageResource(R.drawable.new_game_button_pressed_5by3);
                        try {
                            Thread.sleep(200);
                            Intent intent = new Intent(MainActivity.this, GameActivity.class);
                            intent.putExtra("saveSlot", "no");
                            startActivity(intent);
                            Thread.sleep(200);
                            newGameButton.setImageResource(R.drawable.new_game_button_unpressed_5by3);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        continueButton.setImageResource(R.drawable.continue_button_pressed_5by3);
                        try {
                            Thread.sleep(200);
                            //continue game from save

                            Thread.sleep(200);
                            continueButton.setImageResource(R.drawable.continue_button_unpressed_5by3);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
                toggleSaveView();
            }
        });
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        linkButton.setImageResource(R.drawable.link_button_pressed_5by3);
                        try {
                            Thread.sleep(200);
                            //link to my stuff

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/DanK-R"));
                            startActivity(browserIntent);
                            Thread.sleep(200);
                            linkButton.setImageResource(R.drawable.link_button_unpressed_5by3);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        exitButton.setImageResource(R.drawable.exit_button_pressed_5by3);
                        try {
                            Thread.sleep(200);
                            finish();
                            System.exit(0);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open settings page
                if(settingsLayout.getVisibility() == View.VISIBLE) {
                    settingsLayout.setVisibility(View.INVISIBLE);
                    newGameButton.setEnabled(true);
                    continueButton.setEnabled(true);
                    linkButton.setEnabled(true);
                    exitButton.setEnabled(true);
                }
                else {
                    settingsLayout.setVisibility(View.VISIBLE);
                    newGameButton.setEnabled(false);
                    continueButton.setEnabled(false);
                    linkButton.setEnabled(false);
                    exitButton.setEnabled(false);
                }
            }
        });

        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //Load save 1 action here
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("saveSlot", "save1");
                        startActivity(intent);


                    }
                }.start();
            }
        });

        saveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //Load save 2 action here
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("saveSlot", "save2");
                        startActivity(intent);

                    }
                }.start();
            }
        });

        saveButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //Load save 3 action here
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("saveSlot", "save3");
                        startActivity(intent);

                    }
                }.start();
            }
        });

        saveExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSaveView();

            }
        });
    }

    void toggleSaveView() {
        if(savesLayout.getVisibility() == View.VISIBLE) {
            savesLayout.setVisibility(View.INVISIBLE);
            saveButton1.setEnabled(false);
            saveButton2.setEnabled(false);
            saveButton3.setEnabled(false);
            saveExitButton.setEnabled(false);

            newGameButton.setEnabled(true);
            continueButton.setEnabled(true);
            linkButton.setEnabled(true);
            exitButton.setEnabled(true);

        }
        else {
            savesLayout.setVisibility(View.VISIBLE);
            saveButton1.setEnabled(true);
            saveButton2.setEnabled(true);
            saveButton3.setEnabled(true);
            saveExitButton.setEnabled(true);

            newGameButton.setEnabled(false);
            continueButton.setEnabled(false);
            linkButton.setEnabled(false);
            exitButton.setEnabled(false);

        }
    }

}