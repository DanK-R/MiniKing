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
        Asker a1 = new Asker("Press a Button to Continue.", false, display, yesDutyButton, noExitButton);
        a1.run();
        ResourceKeeper res = new ResourceKeeper(display);
        Questions q = new Questions(res, context);
        gameActivity.runOnUiThread(() -> {

            // Stuff that updates the UI
            //Make the other buttons visible
            mapButton.setVisibility(View.VISIBLE);
            helpButton.setVisibility(View.VISIBLE);

            //Change the button images.
            //to do
        });
        callMenu(q, res);
    }

    private void callMenu(Questions q, ResourceKeeper res) {
        MenuAsker menu = new MenuAsker(menuText, q, res, yesDutyButton, mapButton, helpButton, noExitButton, true, display);
        buttonListenerSetup("menu");
    }

    private void callDuty() {

    }

    private void callMap() {

    }

    private void callHelp() {

    }

    private void callExit() {
        //currently back to main menu
        gameActivity.finish();
    }

    private void buttonListenerSetup(String type) {
        if(Objects.equals(type, "menu")){
            yesDutyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            yesDutyButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                            try {
                                Thread.sleep(200);
                                //call duty here

                                Thread.sleep(200);
                                yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            mapButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                            try {
                                Thread.sleep(200);
                                //call map here

                                Thread.sleep(200);
                                mapButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
            helpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            helpButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                            try {
                                Thread.sleep(200);
                                //call help here

                                Thread.sleep(200);
                                helpButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }.start();
                }
            });
            noExitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            noExitButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                            try {
                                Thread.sleep(200);
                                //call exit here
                                callExit();

                                Thread.sleep(200);
                                noExitButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
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
