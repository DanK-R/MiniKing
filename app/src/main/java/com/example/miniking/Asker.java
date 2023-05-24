package com.example.miniking;//Displays the passed string, then returns true if input is affermative
import java.util.*;

class Asker {
    private Scanner scn;
    private String question;
    private boolean answer;
    private Resources res;
    private boolean clear;

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

    public boolean run() {

        boolean running = true;
        while (running) {
            if(clear) {
                DrawScene.clear();
            }
            DrawScene.open();
            if(res != null) {
                res.draw();
                DrawScene.close();
            }
            
            Printer.printyBox(question);
            DrawScene.close();

            System.out.print("#  " + "Your Reply: ");
            
            String reply = scn.nextLine();
            reply = reply.toLowerCase();

            switch (reply) {
                case "y":
                    this.answer = true;
                    running = false;
                    break;

                case "yes":
                    this.answer = true;
                    running = false;
                    break;

                case "n":
                    this.answer = false;
                    running = false;
                    break;

                case "no":
                    this.answer = false;
                    running = false;
                    break;
                //end to be removed so that end only works at menu
                /*
                case "end":
                    DrawScene.open();
                    Printer.printyBox("                Until Next Time Your Highness");
                    DrawScene.close();
                    System.exit(0);
                */ 
                default:
                    Printer.printyBoxShort("Invalid input, please answer yes or no.");
                    break;
            }
        }
        return answer;
    }
}