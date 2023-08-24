package com.example.miniking;//Main menu of the game
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

class MenuAsker {
    private TextView display;
    private String question;
    private ResourceKeeper res;
    private boolean clear;
    private Questions qList;
    public MenuAsker(String q, Questions qList, ResourceKeeper res, ImageButton dutyButton, ImageButton mapButton, ImageButton helpButton, ImageButton exitButton,
                     boolean clear, TextView display) {
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.qList = qList;
        this.display = display;
    }

    public void run() {
        if(clear) {
            display.setText("");
        }
        visuals();
    }


    //Draw the menu on screen
    private void visuals() {
        DrawScene.open(display);
        if(res != null) {
            res.draw();
            DrawScene.close(display);
        }
        
        DrawScene.open(display);
        Printer.printyBox(question, display);
        DrawScene.close(display);
        
        DrawScene.open(display);
        Printer.printyBox("", display);
        Printer.printyBox("Options:", display);
        Printer.printyBox("", display);
        Printer.printyBox("     Duty:", display);
        Printer.printyBox("            Pass your judgement as Monarch.", display);
        Printer.printyBox("     Map:", display);
        Printer.printyBox("            See the surrounding area.", display);
        Printer.printyBox("     Help:", display);
        Printer.printyBox("            Explination of mechanics.", display);
        Printer.printyBox("     End:",display);
        Printer.printyBox("            Exit the game.", display);
        Printer.printyBox("", display);


        DrawScene.close(display);
    }
}