package com.example.miniking;//Used when no reply is needed
import java.util.*;

class EnterAsker {
    private Scanner scn;
    private String question;
    private String text;
    private boolean clear;

    public EnterAsker(String text, boolean clear) {
        this.scn = new Scanner(System.in);
        this.question = "Press Enter to continue.";
        this.text = text;
        this.clear = clear;
        run();
    }

    public EnterAsker(boolean clear) {
        this.scn = new Scanner(System.in);
        this.question = "Press Enter to continue.";
        this.clear = clear;
        run();
    }

    public void run() {

        boolean running = true;
        while (running) {
            if(clear) {
                DrawScene.clear();
            }
            if(text != null) {
                DrawScene.open();
                Printer.printyBox(text);
                DrawScene.close();
            }
            Printer.printyBox(question);
            DrawScene.close();
            
            String reply = scn.nextLine();
            reply = reply.toLowerCase();

            if(reply.length() == 0) {
                running = false;
            }
            else {
                
            } 
        }
    }
}