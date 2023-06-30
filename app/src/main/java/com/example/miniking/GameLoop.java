package com.example.miniking;

import android.content.Context;
import android.media.Image;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameLoop extends Thread{
    private static String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
    private static String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
    private Context context;
    private TextView display;
    private ImageButton yesButton;
    private ImageButton noButton;

    public GameLoop(Context context, TextView display, ImageButton yesButton, ImageButton noButton) {
        this.context = context;
        this.display = display;
        this.yesButton = yesButton;
        this.noButton = noButton;
    }

    public void run() {
        display.setText("");
        Help h = new Help(display);
        Asker a1 = new Asker("Press a Button to Continue.", false, display, yesButton, noButton);
        a1.run();
        ResourceKeeper res = new ResourceKeeper(display);
        Questions q = new Questions(res, context);
        MenuAsker menu = new MenuAsker(menuText, q, res, true, display);

    }
}
