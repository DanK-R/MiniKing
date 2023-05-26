package com.example.miniking;//Main menu of the game
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.*;

class MenuAsker {
    private TextView display;
    private String question;
    private boolean answer;
    private Resources res;
    private boolean clear;
    private Questions qList;
    private static String menuText2 = "What is on the agenda your Highness?";

    public MenuAsker(String q, Questions qList, Resources res, boolean clear) {
        //this.scn = new Scanner(System.in);
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.qList = qList;
        run();
    }
    public MenuAsker(String q, Questions qList, Resources res, boolean clear, TextView display) {
        //this.scn = new Scanner(System.in);
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.qList = qList;
        this.display = display;
        run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            if(clear) {
                display.setText("");
            }
            //checks if player is in lose state
            if(Fail.check(res)) {
                break; 
            }
            visuals();

            /*
            //process user input
            String reply = scn.nextLine();
            reply = reply.toLowerCase();
            MenuAsker menu;
            switch (reply) {
                case "duty":
                    running = true;
                    Duty.goQuestion(res, qList);
                    break;

                case "map":
                    running = true;
                    Map.mapView(res);
                    break;
                    
                case "help":
                    running = true;
                    Help h = new Help(res);
                    break;

                case "what":
                    running = false;
                    menu = new MenuAsker(menuText2, qList, res, true);
                    menu.run();
                    break;
                    
                case "debug":
                    running = false;
                    menu = new MenuAsker(Debug.debug(res, qList), qList, res, true);
                    menu.run();
                    break;

                case "end":
                    End.save(res, qList);
                    
                default:
                    running = false;
                    menu = new MenuAsker("Sorry I didn't recognize that answer. " + menuText2,  qList, res, true);
                    menu.run();
                    break;
            }

             */
            running = false;
        }
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
        /*
        Printer.printyBox("            Exit game.",display);
        Printer.printyBox("", display);

         */
        DrawScene.close(display);

        Printer.printyBox("#  " + "Your Reply: ", display);
    }
}