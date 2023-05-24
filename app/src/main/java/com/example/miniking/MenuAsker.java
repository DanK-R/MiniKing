package com.example.miniking;//Main menu of the game
import java.util.*;

class MenuAsker {
    private Scanner scn;
    private String question;
    private boolean answer;
    private Resources res;
    private boolean clear;
    private Questions qList;
    private static String menuText2 = "What is on the agenda your Highness?";

    public MenuAsker(String q, Questions qList, Resources res, boolean clear) {
        this.scn = new Scanner(System.in);
        this.question = q;
        this.res = res;
        this.clear = clear;
        this.qList = qList;
        run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            if(clear) {
                DrawScene.clear();
            }
            //checks if player is in lose state
            if(Fail.check(res)) {
                break; 
            }
            visuals();

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
        }
    }

    //Draw the menu on screen
    private void visuals() {
        DrawScene.open();
        if(res != null) {
            res.draw();
            DrawScene.close();
        }
        
        DrawScene.open();
        Printer.printyBox(question);
        DrawScene.close();
        
        DrawScene.open();
        Printer.printyBox("");
        Printer.printyBox("Options:");
        Printer.printyBox("");
        Printer.printyBox("          Duty:");
        Printer.printyBox("                 Pass your judgement as Monarch.");
        Printer.printyBox("          Map:");
        Printer.printyBox("                 See the surrounding area.");
        Printer.printyBox("          Help:");
        Printer.printyBox("                 Explination of mechanics.");
        Printer.printyBox("          End:");
        Printer.printyBox("                 Exit game.");
        Printer.printyBox("");
        DrawScene.close();

        System.out.print("#  " + "Your Reply: ");
    }
}