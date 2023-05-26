package com.example.miniking;//Displays the passed string, then returns true if input is affermative
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.*;

class Asker{
    private Scanner scn;
    private String question;
    private int answer;
    private boolean output;
    private Resources res;
    private boolean clear;
    private ImageButton yesButton;
    private ImageButton noButton;

    private TextView display;

    public Asker(String q, Resources res, boolean clear) {
        this.scn = new Scanner(System.in);
        this.question = q;
        this.res = res;
        this.clear = clear;
    }

    public Asker(String q, boolean clear) {
        this.scn = new Scanner(System.in);
        this.question = q;
        this.clear = clear;
    }

    public Asker(String q, Resources res, boolean clear, TextView display, ImageButton yesButton, ImageButton noButton) {
        this.scn = new Scanner(System.in);
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.display = display;
        this.yesButton = yesButton;
        this.noButton = noButton;
    }

    public Asker(String q, boolean clear, TextView display, ImageButton yesButton, ImageButton noButton) {
        //this.scn = new Scanner(System.in);
        this.question = q;
        this.clear = clear;
        this.display = display;
        this.yesButton = yesButton;
        this.noButton = noButton;
    }

    public boolean run() {
        boolean output = false;
        answer = 0;
        if(clear) {
            display.setText("");
        }
        DrawScene.open(display);
        if(res != null) {
            res.draw();
            DrawScene.close(display);
        }

        Printer.printyBox(question, display);
        DrawScene.close(display);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = 1;
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = 2;
            }
        });

        while (answer == 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(answer == 1) {
                output = true;
                break;
            }
            if(answer == 2) {
                break;
            }
        }
        /*
        switch (reply) {
            case "end":
                DrawScene.open();
                Printer.printyBox("                Until Next Time Your Highness");
                DrawScene.close();
                System.exit(0);

            default:
                Printer.printyBoxShort("Invalid input, please answer yes or no.");
                break;
        }

         */
        return output;
    }
}