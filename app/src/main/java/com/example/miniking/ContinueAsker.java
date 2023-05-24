package com.example.miniking;//Displays the passed string, then returns true if input is affermative
import java.util.*;
import java.util.regex.*;

class ContinueAsker {
    private Scanner scn;
    private String question;
    private boolean answer;
    private Resources res;
    private boolean clear;

    public ContinueAsker(String q, boolean clear) {
        this.scn = new Scanner(System.in);
        this.question = q;
        this.clear = clear;
    }

    public boolean run(int[] x) {

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
                case "end":
                    DrawScene.open();
                        Printer.printyBox("                Until Next Time Your Highness");
                        DrawScene.close();
                        System.exit(0);
                case "cancel":
                    this.answer = false;
                    running = false;
                    break;
                default:
                    Pattern p = Pattern.compile("[1-8]{9}", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(reply);
                    
                    if(m.find()) {
                        this.answer = true;
                        running = false;
                        x[0] = Integer.parseInt(reply);
                        break;
                    }
                    else {
                        this.answer = false;
                            running = false;
                            break;
                    }
            }
        }
        return answer;
    }
}