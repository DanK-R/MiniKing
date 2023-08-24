package com.example.miniking;//Displays the passed string, then returns true if input is affermative
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.*;

class Asker{
    private String question;
    private ResourceKeeper res;
    private boolean clear;

    private TextView display;

    public Asker(String q, ResourceKeeper res, boolean clear, TextView display) {
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.display = display;
    }

    public Asker(String q, boolean clear, TextView display) {
        this.question = q;
        this.clear = clear;
        this.display = display;
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