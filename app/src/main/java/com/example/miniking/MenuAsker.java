package com.example.miniking;//Main menu of the game

import android.widget.TextView;

class MenuAsker {
    private final TextView display;
    private final String question;
    private final ResourceKeeper res;
    private final boolean clear;

    public MenuAsker(String q, ResourceKeeper res,
                     boolean clear, TextView display) {
        this.question = q;
        this.res = res;
        this.clear = clear;
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