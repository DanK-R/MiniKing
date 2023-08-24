package com.example.miniking;//Object that handles the list of situations.

import android.content.Context;

import java.util.*;
import java.io.*;

class Questions {
    private int index;
    private ArrayList<String[]> qList;
    private ResourceKeeper res;
    private Random rand;
    private boolean religionFlag;
    private boolean plagueFlag;
    private boolean magicFlag;
    private Context context;

    //used on new game
    public Questions(ResourceKeeper res, Context context) {
        this.index = 0;
        this.qList = new ArrayList<String[]>();
        this.res = res;
        this.religionFlag = false;
        this.plagueFlag = false;
        this.magicFlag = false;
        this.context = context;
        this.rand = new Random(res.getSeed());

        //parse the data from MainList into questions and shuffle
        //File file = new File(String.valueOf(R.raw.main_list));
        Scanner scnr = new Scanner(context.getResources().openRawResource(R.raw.main_list));
        while(scnr.hasNextLine()) {
            String data = scnr.nextLine();
            String[] dataParsed = data.split("@",5);
            qList.add(dataParsed);
        }
        scnr.close();
        Collections.shuffle(qList, rand);
    }

    //used when loading a save or choosing a specific seed
    public Questions(ResourceKeeper res, int index, boolean religionFlag, boolean plagueFlag, boolean magicFlag,Context context) {
        this.index = index;
        this.qList = new ArrayList<String[]>();
        this.res = res;
        this.context = context;
        this.religionFlag = religionFlag;
        this.plagueFlag = plagueFlag;
        this.magicFlag = magicFlag;
        this.rand = new Random(res.getSeed());

        //parse the data from MainList into questions
        //File file = new File(String.valueOf(R.raw.main_list));
        Scanner scnr = new Scanner(context.getResources().openRawResource(R.raw.main_list));
        int i = 0;
        while (scnr.hasNextLine()) {
            String data = scnr.nextLine();
            String[] dataParsed = data.split("@",5);
            qList.add(dataParsed);
            i++;
        }
        scnr.close();
        Collections.shuffle(qList, rand);

        //if needed add the other lists of questions
        if(religionFlag || plagueFlag || magicFlag) {
            listUpdater();
        }
    }

    
    //gets next question in list
    public String getNext() {
        int indexTemp;
        if(index >= qList.size()) {
            indexTemp = qList.size() - 1;
        }
        else {
            indexTemp = index;
        }
        String out = qList.get(indexTemp)[0];
        return out;
    }

    //process either outcome and return the resulting text, increment index, and calls listUpdater if needed
    public String outcome1() {
        String t = qList.get(index)[1];
        applyOutcome(qList.get(index)[3]);
        if(index < qList.size() - 1) {
            index++;
        }
        if(religionFlag || plagueFlag || magicFlag) {
            listUpdater();
        }
        return t;
    }
    public String outcome2() {
        String t = qList.get(index)[2];
        applyOutcome(qList.get(index)[4]);
        if(index < qList.size() - 1) {
            index++;
        }
        
        if(religionFlag || plagueFlag || magicFlag) {
            listUpdater();
        }
        return t;
    }

    public int getIndex() {
        return this.index;
    }

    //interprets the keywords laced within the Tomes
    private void applyOutcome(String out) {
        String[] outDataParsed = out.split("#");
        for(int k = 0; k < outDataParsed.length; k++) {
            switch(outDataParsed[k]) {
                case "O+":
                    res.setOrder(1);
                    break;
                
                case "O-":
                    res.setOrder(-1);
                    break;

                case "F+":
                    res.setFood(1);
                    break;
                
                case "F-":
                    res.setFood(-1);
                    break;

                case "G+":
                    res.setGold(1);
                    break;
                
                case "G-":
                    res.setGold(-1);
                    break;

                case "M+":
                    res.setMight(1);
                    break;
                
                case "M-":
                    res.setMight(-1);
                    break;

                case "O++":
                    res.setOrder(2);
                    break;
                
                case "O--":
                    res.setOrder(-2);
                    break;

                case "F++":
                    res.setFood(2);
                    break;
                
                case "F--":
                    res.setFood(-2);
                    break;

                case "G++":
                    res.setGold(2);
                    break;
                
                case "G--":
                    res.setGold(-2);
                    break;

                case "M++":
                    res.setMight(2);
                    break;
                
                case "M--":
                    res.setMight(-2);
                    break;

                case "O+++":
                    res.setOrder(3);
                    break;
                
                case "O---":
                    res.setOrder(-3);
                    break;

                case "F+++":
                    res.setFood(3);
                    break;
                
                case "F---":
                    res.setFood(-3);
                    break;

                case "G+++":
                    res.setGold(3);
                    break;
                
                case "G---":
                    res.setGold(-3);
                    break;

                case "M+++":
                    res.setMight(3);
                    break;
                
                case "M---":
                    res.setMight(-3);
                    break;

                case "r":
                    this.religionFlag = true;
                    break;

                case "p":
                    this.plagueFlag = true;
                    break;
                
                case "m":
                    this.magicFlag = true;
                    break;
                
                default:
                    break;
            }
        }
    }


    //returns int values for the flags
    public int getReligionFlag() {
        if(religionFlag)
            return 1;
        else
            return 0;
    }
    public int getPlagueFlag() {
        if(plagueFlag)
            return 1;
        else
            return 0;
    }
    public int getMagicFlag() {
        if(magicFlag)
            return 1;
        else
            return 0;
    }

    
    private void listUpdater() {
        //add answered ultimatums to the outList
        ArrayList<String[]> outList = new ArrayList<String[]>();
        for(int i = 0; i < index; i++) {
            outList.add(qList.get(i));
        }

        //add future ultimatums to tempList
        ArrayList<String[]> tempList = new ArrayList<String[]>();
        for(int i = index; i < qList.size(); i++) {
            tempList.add(qList.get(i));
        }
        
        //add the religion questions to templist if religionFlag is raised
        if(religionFlag) {
            Scanner scnr = new Scanner(context.getResources().openRawResource(R.raw.religion_list));
            while (scnr.hasNextLine()) {
                String data = scnr.nextLine();
                String[] dataParsed = data.split("@",5);
                tempList.add(dataParsed);
            }
            scnr.close();
        }
        //shuffle the resulting religion + upcoming list
        Random r = new Random(res.getSeed());
        Collections.shuffle(tempList, r);

        //add shuffled list to the outlist
        for(int i = 0; i < tempList.size(); i++) {
            outList.add(tempList.get(i));
        }
        //replace old qList with outList
        this.qList = outList;
    } 
}