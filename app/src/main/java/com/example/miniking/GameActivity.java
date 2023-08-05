package com.example.miniking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    GameActivity gameActivity;
    private final String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
    private final String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
    private TextView display;
    private ImageButton yesDutyButton;
    private ImageButton noExitButton;
    private ImageButton mapButton;
    private ImageButton helpButton;
    private ImageButton settingsButton;
    private boolean acknowledged;
    private boolean response;
    private ResourceKeeper res;
    private Questions q;
    private boolean in4ButtonLayout;
    private FrameLayout settingsLayout;
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

        //initialize the variables
        yesDutyButton = findViewById(R.id.yesDutyButton);
        noExitButton = findViewById(R.id.noExitButton);
        settingsButton = findViewById(R.id.settingsButton);
        mapButton = findViewById(R.id.mapButton);helpButton = findViewById(R.id.helpButton);
        settingsLayout = findViewById(R.id.settingsLayout);
        display = findViewById(R.id.dialogTextView);
        gameActivity = GameActivity.this;

        //hide the two other buttons
        mapButton.setVisibility(View.GONE);
        helpButton.setVisibility(View.GONE);
        res = new ResourceKeeper(display);
        q = new Questions(res, this);

        buttonListenerSetup();
        gameActivity.runOnUiThread(() -> {
            display.setText("");
        });





        new Thread() {
            public void run() {
                Help h = new Help(display);
                Asker a1 = new Asker("Press a Button to Continue.", false, display);
                a1.draw();

                if(mapButton.getVisibility() == View.VISIBLE){
                    toggleButtonLayout();
                }
                acknowledged = false;
                while(!acknowledged) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                callMenu();
            }
        }.start();
    }

    private void callMenu() {
        MenuAsker menu = new MenuAsker(menuText, q, res, yesDutyButton, mapButton, helpButton, noExitButton, true, display);
        menu.run();
        if(mapButton.getVisibility() == View.GONE) {
            toggleButtonLayout();
        }
    }

    private void callDuty() {
        Duty duty = new Duty(res, q, display);
        duty.nextQuestion();
        new Thread() {
            public void run() {
                if(mapButton.getVisibility() == View.VISIBLE){
                    toggleButtonLayout();
                }
                acknowledged = false;
                while(!acknowledged) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                duty.outcome(response);

                acknowledged = false;
                while(!acknowledged) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                callMenu();
            }
        }.start();
    }

    private void callMap() {
        Map.mapView(res, display);
        Asker asker = new Asker("",false, display);
        asker.acknowledge();
        new Thread() {
            public void run() {
                if(mapButton.getVisibility() == View.VISIBLE){
                    toggleButtonLayout();
                }
                acknowledged = false;
                while(!acknowledged) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                callMenu();
            }
        }.start();
    }

    private void callHelp() {
        gameActivity.runOnUiThread(() -> {
            display.setText("");
        });
        Help help = new Help(display);
        Asker asker = new Asker("",false, display);
        asker.acknowledge();
        new Thread() {
            public void run() {
                if(mapButton.getVisibility() == View.VISIBLE){
                    toggleButtonLayout();
                }
                acknowledged = false;
                while(!acknowledged) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                callMenu();
            }
        }.start();
    }

    private void callExit() {
        //currently exits to main but like weird
        //need to add save prompt
        gameActivity.finish();
        System.exit(0);
    }

    private boolean callAcknowledgement() {
        //changes the buttons to just acknowledge the prompt
        //changes the layout to 2 button
        //returns true or false

        if(mapButton.getVisibility() == View.VISIBLE){
            toggleButtonLayout();
        }

        waitForAcknowledgment();

        return response;
    }

    private void waitForAcknowledgment() {
        acknowledged = false;
        new Thread() {
            public void run() {
                while(!acknowledged) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }

    private void toggleButtonLayout() {
        gameActivity.runOnUiThread(() -> {
            //toggle button visibility
            //if=4button, else=2button
            if(mapButton.getVisibility() == View.GONE){
                mapButton.setEnabled(true);
                mapButton.setVisibility(View.VISIBLE);
                helpButton.setEnabled(true);
                helpButton.setVisibility(View.VISIBLE);
                yesDutyButton.setImageResource(R.drawable.duty_button_unpressed_5by3);
                noExitButton.setImageResource(R.drawable.exit_button_unpressed_5by3);
                in4ButtonLayout = true;
            }
            else {
                mapButton.setEnabled(false);
                mapButton.setVisibility(View.GONE);
                helpButton.setEnabled(false);
                helpButton.setVisibility(View.GONE);
                yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                noExitButton.setImageResource(R.drawable.no_button_unpressed_5by3);
                in4ButtonLayout = false;
            }

            //Change the button images.
            //to do in the listenerSetup
        });
    }

    private void buttonListenerSetup() {
        setSettingsButton();
        setYesDutyButton();
        setMapButton();
        setHelpButton();
        setNoExitButton();
    }

    private void setYesDutyButton() {

        yesDutyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        if(in4ButtonLayout){
                            gameActivity.runOnUiThread(() -> {
                                yesDutyButton.setImageResource(R.drawable.duty_button_pressed_5by3);
                                try {
                                    Thread.sleep(400);
                                    //call duty here


                                    yesDutyButton.setImageResource(R.drawable.duty_button_unpressed_5by3);
                                    callDuty();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                        else {
                            gameActivity.runOnUiThread(() -> {
                                yesDutyButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                                try {
                                    Thread.sleep(400);



                                    yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                                    response = true;
                                    acknowledged = true;
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                    }
                }.start();
            }
        });
    }

    private void setMapButton() {
        gameActivity.runOnUiThread(() -> {
            mapButton.setImageResource(R.drawable.map_button_unpressed_5by3);
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        gameActivity.runOnUiThread(() -> {
                            mapButton.setImageResource(R.drawable.map_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call map here
                                callMap();

                                mapButton.setImageResource(R.drawable.map_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }.start();
            }
        });
    }

    private void setHelpButton() {
        gameActivity.runOnUiThread(() -> {
            helpButton.setImageResource(R.drawable.help_button_unpressed_5by3);
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        gameActivity.runOnUiThread(() -> {
                            helpButton.setImageResource(R.drawable.help_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call help here
                                callHelp();

                                helpButton.setImageResource(R.drawable.help_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }.start();
            }
        });
    }

    private void setNoExitButton() {
        noExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        if(in4ButtonLayout) {
                            gameActivity.runOnUiThread(() -> {
                                noExitButton.setImageResource(R.drawable.exit_button_pressed_5by3);
                                try {
                                    Thread.sleep(400);
                                    //call exit here


                                    noExitButton.setImageResource(R.drawable.exit_button_unpressed_5by3);
                                    callExit();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                        else {
                            gameActivity.runOnUiThread(() -> {
                                noExitButton.setImageResource(R.drawable.no_button_pressed_5by3);
                                try {
                                    Thread.sleep(400);



                                    noExitButton.setImageResource(R.drawable.no_button_unpressed_5by3);
                                    response = false;
                                    acknowledged = true;
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                    }
                }.start();
            }
        });
    }

    public void setSettingsButton() {
        settingsButton.setOnClickListener(v -> {
            //open settings page
            if(settingsLayout.getVisibility() == View.VISIBLE) {
                gameActivity.runOnUiThread(() -> {
                    yesDutyButton.setEnabled(true);
                    noExitButton.setEnabled(true);
                    if(mapButton.getVisibility() == View.VISIBLE) {
                        mapButton.setEnabled(true);
                        helpButton.setEnabled(true);
                    }
                    settingsLayout.setVisibility(View.INVISIBLE);
                });

            }
            else {
                gameActivity.runOnUiThread(() -> {
                    yesDutyButton.setEnabled(false);
                    noExitButton.setEnabled(false);
                    if(mapButton.getVisibility() == View.VISIBLE) {
                        mapButton.setEnabled(false);
                        helpButton.setEnabled(false);
                    }
                    settingsLayout.setVisibility(View.VISIBLE);
                });
            }
        });
    }
}