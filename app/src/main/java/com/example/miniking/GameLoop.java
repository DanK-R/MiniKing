package com.example.miniking;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Objects;

public class GameLoop extends Thread{
    private static String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
    private static String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
    private Context context;
    private TextView display;
    private ImageButton yesDutyButton;
    private ImageButton noExitButton;
    private ImageButton mapButton;
    private ImageButton helpButton;
    private GameActivity gameActivity;
    private boolean acknowledged;
    private boolean response;

    public GameLoop(Context context, TextView display, ImageButton yesDutyButton, ImageButton noExitButton, ImageButton mapButton, ImageButton helpButton, GameActivity gameActivity) {
        this.context = context;
        this.display = display;
        this.yesDutyButton = yesDutyButton;
        this.noExitButton = noExitButton;
        this.mapButton = mapButton;
        this.helpButton = helpButton;
        this.gameActivity = gameActivity;
    }


    public void run() {
        display.setText("");
        Help h = new Help(display);
        Asker a1 = new Asker("Press a Button to Continue.", false, display);
        a1.draw();
        callAcknowledgement();

        ResourceKeeper res = new ResourceKeeper(display);
        Questions q = new Questions(res, context);

        callMenu(q, res);
    }

    private void callMenu(Questions q, ResourceKeeper res) {
        MenuAsker menu = new MenuAsker(menuText, q, res, yesDutyButton, mapButton, helpButton, noExitButton, true, display);
        buttonListenerSetup("menu");
        if(mapButton.getVisibility() == View.GONE) {
            toggleButtonLayout();
        }
    }

    private void callDuty() {

    }

    private void callMap() {

    }

    private void callHelp() {

        buttonListenerSetup("acknowledge");
    }

    private void callExit() {
        //currently back to main menu
        gameActivity.finish();
    }

    private boolean callAcknowledgement() {
        //changes the buttons to just acknowledge the prompt
        //changes the layout to 2 button
        //returns true or false
        buttonListenerSetup("acknowledge");

        if(mapButton.getVisibility() == View.VISIBLE){
            toggleButtonLayout();
        }

        waitForAcknowledgment();

        return response;
    }

    private void waitForAcknowledgment() {
        acknowledged = false;

        while(!acknowledged) {
            try {
                currentThread().sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void toggleButtonLayout() {
        gameActivity.runOnUiThread(() -> {
            //toggle button visibility
            if(mapButton.getVisibility() == View.GONE){
                mapButton.setEnabled(true);
                mapButton.setVisibility(View.VISIBLE);
                helpButton.setEnabled(true);
                helpButton.setVisibility(View.VISIBLE);
            }
            else {
                mapButton.setEnabled(false);
                mapButton.setVisibility(View.GONE);
                helpButton.setEnabled(false);
                helpButton.setVisibility(View.GONE);
            }

            //Change the button images.
            //to do in the listenerSetup
        });
    }

    private void buttonListenerSetup(String type) {
        if(Objects.equals(type, "menu")){
            yesDutyButton.setImageResource(R.drawable.duty_button_unpressed_5by3);
            yesDutyButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            yesDutyButton.setImageResource(R.drawable.duty_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call duty here

                                yesDutyButton.setImageResource(R.drawable.duty_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
            mapButton.setImageResource(R.drawable.map_button_unpressed_5by3);
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            mapButton.setImageResource(R.drawable.map_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call map here

                                mapButton.setImageResource(R.drawable.map_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
            helpButton.setImageResource(R.drawable.help_button_unpressed_5by3);
            helpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            helpButton.setImageResource(R.drawable.help_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call help here

                                helpButton.setImageResource(R.drawable.help_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
            noExitButton.setImageResource(R.drawable.exit_button_unpressed_5by3);
            noExitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            noExitButton.setImageResource(R.drawable.exit_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call exit here
                                callExit();

                                noExitButton.setImageResource(R.drawable.exit_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
        }

        else if(Objects.equals(type, "acknowledge")){
            //switch to 2 button yes or no
            yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
            yesDutyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            yesDutyButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                            try {
                                Thread.sleep(400);

                                response = true;
                                acknowledged = true;

                                yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });

            noExitButton.setImageResource(R.drawable.no_button_unpressed_5by3);
            noExitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            noExitButton.setImageResource(R.drawable.no_button_pressed_5by3);
                            try {
                                Thread.sleep(400);

                                response = false;
                                acknowledged = true;

                                noExitButton.setImageResource(R.drawable.no_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
        }
    }
}
