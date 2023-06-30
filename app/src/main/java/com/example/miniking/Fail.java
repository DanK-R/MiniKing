package com.example.miniking;//returns true if the player is in fail state
import java.io.*;

class Fail {
    public static boolean check(ResourceKeeper res) {
        if(res.getOrder() <= 0 || 
            res.getFood() <= 0 ||
            res.getGold() <= 0 ||
            res.getMight() <= 0 ||
            res.getOrder() >= 30 ||
            res.getFood() >= 30 ||
            res.getGold() >= 30 ||
            res.getMight() >= 30 ||
            res.getTime() == 45) {
            return true;
            
        }
        else {
            return false;
        }
    }

    public static void endGame(ResourceKeeper res, Questions q) {
        DrawScene.open();
        System.out.println(Printer.Middle("Until Next Time Your Highness", 65));
        
        try {
            File file = new File("Temp/" + res.getSeed() + ".txt");
            FileWriter fileWriter;
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                fileWriter = new FileWriter("Temp/" + res.getSeed() + ".txt");
                fileWriter.write(Integer.toString(q.getIndex()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getTime()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getOrder()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getFood()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getGold()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getMight()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getSeed()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(q.getReligionFlag()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(q.getPlagueFlag()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(q.getMagicFlag()));
                //add fileWriter.write("@"); to denote new token
            } 
            else {
                System.out.println(Printer.Middle("Updated Your Save", 65));
                file.delete();
                file.createNewFile();
                fileWriter = new FileWriter("Temp/" + res.getSeed() + ".txt");
                fileWriter.write(Integer.toString(q.getIndex()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getTime()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getOrder()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getFood()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getGold()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getMight()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(res.getSeed()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(q.getReligionFlag()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(q.getPlagueFlag()));
                fileWriter.write("@");
                fileWriter.write(Integer.toString(q.getMagicFlag()));
                //add fileWriter.write("@"); to denote new token
                
            }
            fileWriter.close();
        } 
        catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
            
        Printer.printyBox("Your Password is " + res.getSeed() + " Save it for next time to continue");
        DrawScene.close();
        System.exit(0);
    }
}

