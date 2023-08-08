package com.example.miniking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

public class GameActivity extends AppCompatActivity {
    GameActivity gameActivity;
    private static final String TAG = "GameActivity";
    private final String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
    private final String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
    private TextView display;
    private int[] NPCs = {
            R.drawable.elder_1,

            R.drawable.aristocrat_01,

            R.drawable.aristocrat_02,

            R.drawable.barkeep,

            R.drawable.captain,

            R.drawable.farmer,

            R.drawable.guard,

            R.drawable.masked_man,

            R.drawable.merchant,

            R.drawable.stranger,

            R.drawable.trader_1,

            R.drawable.villager_01
    };
    private ImageButton yesDutyButton;
    private ImageButton noExitButton;
    private ImageButton mapButton;
    private ImageButton helpButton;
    private ImageButton settingsButton;
    private ImageButton saveButton1;
    private ImageButton saveButton2;
    private ImageButton saveButton3;
    private ImageButton saveExitButton;
    private boolean acknowledged;
    private boolean response;
    private ResourceKeeper res;
    private Questions q;
    private boolean in4ButtonLayout;
    private FrameLayout settingsLayout;
    private FrameLayout savesLayout;
    private String saveSlot;
    private boolean saveHold;
    private static GifImageView npcWindow;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
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
        mapButton = findViewById(R.id.mapButton);
        helpButton = findViewById(R.id.helpButton);
        saveButton1 = findViewById(R.id.saveButton1);
        saveButton2 = findViewById(R.id.saveButton2);
        saveButton3 = findViewById(R.id.saveButton3);
        saveExitButton = findViewById(R.id.saveExitButton);
        npcWindow = findViewById(R.id.npcWindow);

        settingsLayout = findViewById(R.id.settingsLayout);
        savesLayout = findViewById(R.id.savesLayout);
        display = findViewById(R.id.dialogTextView);
        gameActivity = GameActivity.this;
        saveHold = true;

        //hide the two other buttons
        mapButton.setVisibility(View.GONE);
        helpButton.setVisibility(View.GONE);

        String saveSlot = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            saveSlot = extras.getString("saveSlot");
            if(saveSlot.equals("no")){
                res = new ResourceKeeper(display);
                q = new Questions(res, this);
            }
            else{
                SaveDataParser sdp = new SaveDataParser(
                        this, null, null, saveSlot);
                JSONObject saveJSON = sdp.getJSON();
                try {
                    int index = saveJSON.getInt("index");
                    int time = saveJSON.getInt("time");
                    int order = saveJSON.getInt("order");
                    int food = saveJSON.getInt("food");
                    int gold = saveJSON.getInt("gold");
                    int might = saveJSON.getInt("might");
                    int seed = saveJSON.getInt("seed");
                    int religionFlag = saveJSON.getInt("religionFlag");
                    int magicFlag = saveJSON.getInt("magicFlag");
                    int plagueFlag = saveJSON.getInt("plagueFlag");



                    res = new ResourceKeeper
                            (time, order, food, gold, might,seed, display);
                    q = new Questions(
                            res, index, intToBool(religionFlag),
                            intToBool(plagueFlag), intToBool(magicFlag), this);
                } catch (JSONException e) {
                    Log.e(TAG, String.valueOf(e));
                    throw new RuntimeException(e);
                }
            }
        }

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
        setNPCView(R.drawable.elder_1);
        if(!inFailState()){
            MenuAsker menu = new MenuAsker(menuText, q, res, yesDutyButton, mapButton, helpButton, noExitButton, true, display);
            menu.run();
            if (mapButton.getVisibility() == View.GONE) {
                toggleButtonLayout();
            }
            new Thread() {
                public void run() {
                    try {
                        currentThread().sleep(100);
                        if (mapButton.getVisibility() == View.GONE) {
                            toggleButtonLayout();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }.start();
        }
        else {
            //do win/lose content

            callExit();
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
        if(!inFailState()) {
            callSaveDialog();
        }
        else {
            gameActivity.finish();
            System.exit(0);
        }
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
        setSaveButton1();
        setSaveButton2();
        setSaveButton3();
        setSaveExitButton();
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

    private void setSettingsButton() {
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

    private void setSaveButton1() {
        saveButton1.setOnClickListener(v -> {
            saveSlot = "save1";
            saveHold = false;
        });
    }

    private void setSaveButton2() {
        saveButton2.setOnClickListener(v -> {
            saveSlot = "save2";
            saveHold = false;
        });
    }

    private void setSaveButton3() {
        saveButton3.setOnClickListener(v -> {
            saveSlot = "save3";
            saveHold = false;
        });
    }

    private void setSaveExitButton() {
        saveExitButton.setOnClickListener(v -> {
            saveSlot = "no";
            saveHold = false;
        });
    }

    private boolean inFailState() {
        if(res.getOrder() <= 0 ||
                res.getFood() <= 0 ||
                res.getGold() <= 0 ||
                res.getMight() <= 0 ||
                res.getOrder() >= 30 ||
                res.getFood() >= 30 ||
                res.getGold() >= 30 ||
                res.getMight() >= 30 ||
                res.getTime() == 45) {
            return true;

        }
        else {
            return false;
        }
    }

    private void callSaveDialog() {
        toggleSaveView();
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            public void run() {
                while(saveHold) {
                    try {
                        currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(!saveSlot.equals("no")){
                    SaveDataParser saver = new SaveDataParser(gameActivity, res, q, saveSlot);
                    saver.saveGame();
                }
                gameActivity.finish();
                System.exit(0);
            }
        }.start();

    }

    void toggleSaveView() {
        if(savesLayout.getVisibility() == View.VISIBLE) {
            savesLayout.setVisibility(View.INVISIBLE);
            saveButton1.setEnabled(false);
            saveButton2.setEnabled(false);
            saveButton3.setEnabled(false);
            saveExitButton.setEnabled(false);

            yesDutyButton.setEnabled(true);
            mapButton.setEnabled(true);
            helpButton.setEnabled(true);
            noExitButton.setEnabled(true);

        }
        else {
            savesLayout.setVisibility(View.VISIBLE);
            saveButton1.setEnabled(true);
            saveButton2.setEnabled(true);
            saveButton3.setEnabled(true);
            saveExitButton.setEnabled(true);

            yesDutyButton.setEnabled(false);
            mapButton.setEnabled(false);
            helpButton.setEnabled(false);
            noExitButton.setEnabled(false);

        }
    }

    private boolean intToBool(int x) {
        if(x == 0) {
            return false;
        }
        return true;
    }

    //usage setNPCView(R.drawable.resource);
    public static void setNPCView(int gif) {
        npcWindow.setImageResource(gif);
    }
}