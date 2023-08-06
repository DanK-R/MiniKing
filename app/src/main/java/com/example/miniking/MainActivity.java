package com.example.miniking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
                            startActivity(new Intent(MainActivity.this, GameActivity.class));
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
//                    leftButtonsLayout.setVisibility(View.VISIBLE);
//                    rightButtonsLayout.setVisibility(View.VISIBLE);
                }
                else {
                    settingsLayout.setVisibility(View.VISIBLE);
                    newGameButton.setEnabled(false);
                    continueButton.setEnabled(false);
                    linkButton.setEnabled(false);
                    exitButton.setEnabled(false);
//                    leftButtonsLayout.setVisibility(View.INVISIBLE);
//                    rightButtonsLayout.setVisibility(View.INVISIBLE);
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