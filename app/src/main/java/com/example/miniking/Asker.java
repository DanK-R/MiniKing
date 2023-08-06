package com.example.miniking;//Displays the passed string, then returns true if input is affermative
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.*;

class Asker{
    private Scanner scn;
    private String question;
    private int answer;
    private boolean output;
    private ResourceKeeper res;
    private boolean clear;
    private ImageButton yesButton;
    private ImageButton noButton;

    private TextView display;

    public Asker(String q, ResourceKeeper res, boolean clear) {
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

    public Asker(String q, ResourceKeeper res, boolean clear, TextView display, ImageButton yesButton, ImageButton noButton) {
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

    public Asker(String q, ResourceKeeper res, boolean clear, TextView display) {
        this.scn = new Scanner(System.in);
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.display = display;
    }

    public Asker(String q, boolean clear, TextView display) {
        //this.scn = new Scanner(System.in);
        this.question = q;
        this.clear = clear;
        this.display = display;
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
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        yesButton.setImageResource(R.drawable.yes_button_pressed_5by3);
                        try {
                            Thread.sleep(400);
                            yesButton.setImageResource(R.drawable.yes_button_unpressed_5by3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = 2;
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        noButton.setImageResource(R.drawable.no_button_pressed_5by3);
                        try {
                            Thread.sleep(400);
                            noButton.setImageResource(R.drawable.no_button_unpressed_5by3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }.start();
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

    public void draw() {
        if(clear) {
            display.setText("");
        }
        DrawScene.open(display);
        if(res != null) {
            res.draw();
            DrawScene.close(display);
        }

        Printer.printyBoxReplacer(question, res, display);
        DrawScene.close(display);

    }

    public void acknowledge() {
        Printer.printyBox("Press any button to continue.", display);
        DrawScene.close(display);
    }

    public void choice() {
        Printer.printyBox("Make your decision.", display);
        DrawScene.close(display);
    }
}