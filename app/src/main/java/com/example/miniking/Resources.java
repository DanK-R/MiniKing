package com.example.miniking;

import java.util.*;
import java.io.*;

class Resources {
    private int time;
    private int order;
    private int food;
    private int gold;
    private int might;
    private int seed;
    private Random rand;
    private String rName;

    //used on new game
    public Resources() {
        this.time = 1;
        this.order = 15;
        this.food = 15;
        this.gold = 15;
        this.might = 15;

        //create unique random seed - 9 ints each 1-8, 134,217,728 possible outcomes
        //100 tries at a new seed else it terminates
        Random r = new Random();
        boolean running = true;
        for(int j = 0; running; j++) {
            StringBuffer sb = new StringBuffer();
            
            for(int i = 0; i < 9; i++) {
                sb.append(r.nextInt(7) + 1);
            }
            
            File file = new File("Temp/" + sb.toString() + ".txt");
            if (!file.exists()) {
                this.seed = Integer.parseInt(sb.toString());
                running = false;
            }
            if(j == 100) {
                System.out.println("Unique Seed Generation Failure");
                System.exit(0);
            }
        }  

        //seeded random rival name
        this.rand = new Random(seed);
        this.rName = (NameGen.rivalName(rand));
    }

    //used when loading games or choosing custom seed
    public Resources(int time, int order, int food, int gold, int might, int seed) {
        this.time = time;
        this.order = order;
        this.food = food;
        this.gold = gold;
        this.might = might;
        this.seed = seed;
        this.rand = new Random(seed);
        this.rName = (NameGen.rivalName(rand));
    }

    //display the resource values
    public void draw() {
        Printer.printyBox("Years In Power: " + this.time);     
        Printer.printyBox("Order: " + meter(this.order));
        Printer.printyBoxShort("Food:  " + meter(this.food));
        Printer.printyBoxShort("Gold:  " + meter(this.gold));
        Printer.printyBoxShort("Might: " + meter(this.might));
        
    }

    //bar meter ascii
    private String meter(int value) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < 30; i++) {
            if(i < value) {
                sb.append("■");
            }
            else {
                sb.append("□");
            }
        }
        return sb.toString();
    }

    
    public void setOrder(int mod) {
        this.order = order + mod;
    }

    public void setFood(int mod) {
        this.food = food + mod;
    }

    public void setGold(int mod) {
        this.gold = gold + mod;
    }

    public void setMight(int mod) {
        this.might = might + mod;
    }

    public void passTime() {
        time++;
    }

    public int getOrder() {
        return this.order;
    }

    public int getFood() {
        return this.food;
    }

    public int getGold() {
        return this.gold;
    }

    public int getMight() {
        return this.might;
    }

    public int getTime() {
        return this.time;
    }

    public int getSeed() {
        return this.seed;
    }

    public String getRivalName() {
        return rName;
    }
    
    //get the specified index of the seed
    public int getSeedIndex(int x) {       
        return Integer.parseInt(String.valueOf(Integer.toString(seed).charAt(x)));
    }

}
