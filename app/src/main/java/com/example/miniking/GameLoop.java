package com.example.miniking;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;

import java.lang.annotation.Annotation;

public class GameLoop extends Thread {
    private final String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
    private final String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
    private Context context;
    private TextView display;
    private ImageButton yesDutyButton;
    private ImageButton noExitButton;
    private ImageButton mapButton;
    private ImageButton helpButton;
    private GameActivity gameActivity;
    private boolean acknowledged;
    private boolean response;
    private ResourceKeeper res;
    private Questions q;
    private boolean in4ButtonLayout;

    public GameLoop(Context context, TextView display, ImageButton yesDutyButton, ImageButton noExitButton, ImageButton mapButton, ImageButton helpButton, GameActivity gameActivity) {
        this.context = context;
        this.display = display;
        this.yesDutyButton = yesDutyButton;
        this.noExitButton = noExitButton;
        this.mapButton = mapButton;
        this.helpButton = helpButton;
        this.gameActivity = gameActivity;
        this.in4ButtonLayout = false;
    }


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void run() {
        mapButton.setVisibility(View.INVISIBLE);
        helpButton.setVisibility(View.INVISIBLE);
        buttonListenerSetup();
        display.setText("");
        Help h = new Help(display);
        Asker a1 = new Asker("Press a Button to Continue.", false, display);
        a1.draw();
        callAcknowledgement();

        res = new ResourceKeeper(display);
        q = new Questions(res, context);

        callMenu();

//        SaveDataParser saveDataParser = new SaveDataParser( context, String.valueOf(res.getSeed()),res, q);
//        saveDataParser.saveGame();
//        saveDataParser.openGame();

    }




    private void callMenu() {
        MenuAsker menu = new MenuAsker(menuText, q, res, yesDutyButton, mapButton, helpButton, noExitButton, true, display);

        if(mapButton.getVisibility() == View.INVISIBLE) {
            toggleButtonLayout();
        }
    }

    private void callDuty() {
        toggleButtonLayout();
        Duty duty = new Duty(res, q, display);
        duty.nextQuestion();
        duty.outcome(callAcknowledgement());
        callAcknowledgement();
        callMenu();
    }

    private void callMap() {
        Map.mapView(res, display);
        Asker asker = new Asker("",false, display);
        asker.acknowledge();
        callAcknowledgement();
        display.setText("");
        callMenu();
    }

    private void callHelp() {
        display.setText("");
        Help help = new Help(display);
        Asker asker = new Asker("",false, display);
        asker.acknowledge();
        callAcknowledgement();
        display.setText("");
        callMenu();
    }

    private void callExit() {
        //currently back to main menu
        gameActivity.finish();
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
            //if=4button, else=2button
            if(mapButton.getVisibility() == View.INVISIBLE){
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
                mapButton.setVisibility(View.INVISIBLE);
                helpButton.setEnabled(false);
                helpButton.setVisibility(View.INVISIBLE);
                yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                noExitButton.setImageResource(R.drawable.no_button_unpressed_5by3);
                in4ButtonLayout = false;
            }

            //Change the button images.
            //to do in the listenerSetup
        });
    }

    private void buttonListenerSetup() {
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
                            yesDutyButton.setImageResource(R.drawable.duty_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call duty here


                                yesDutyButton.setImageResource(R.drawable.duty_button_unpressed_5by3);
                                callDuty();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            yesDutyButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                            try {
                                Thread.sleep(400);



                                yesDutyButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                                response = true;
                                acknowledged = true;
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                }.start();
            }
        });
    }

    private void setMapButton() {
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
                            callMap();

                            mapButton.setImageResource(R.drawable.map_button_unpressed_5by3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
            }
        });
    }

    private void setHelpButton() {
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
                            callHelp();

                            helpButton.setImageResource(R.drawable.help_button_unpressed_5by3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

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
                            noExitButton.setImageResource(R.drawable.exit_button_pressed_5by3);
                            try {
                                Thread.sleep(400);
                                //call exit here


                                noExitButton.setImageResource(R.drawable.exit_button_unpressed_5by3);
                                callExit();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            noExitButton.setImageResource(R.drawable.no_button_pressed_5by3);
                            try {
                                Thread.sleep(400);



                                noExitButton.setImageResource(R.drawable.no_button_unpressed_5by3);
                                response = false;
                                acknowledged = true;
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                }.start();
            }
        });
    }
}
