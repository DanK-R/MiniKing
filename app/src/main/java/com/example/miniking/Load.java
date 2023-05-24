package com.example.miniking;

import java.io.*;
import java.util.*;

class Load {
    public static int[] load(int [] x) {
        int[] data = new int[1];
        try {
            File file = new File("Temp/" + Integer.toString(x[0]) + ".txt");
            if(file.exists()) {
                FileReader fileReader = new FileReader(file);
                char[] stream = new char[1000];
                String[] nextParsed = new String[1];
                fileReader.read(stream);
                stream.toString();
                Scanner scnr = new Scanner(file);
                while(scnr.hasNextLine()) {
                    String next = scnr.nextLine();
                    nextParsed = next.split("@");
                }
                data = new int[nextParsed.length];
                for(int i = 0; i < nextParsed.length; i++) {
                    data[i] = Integer.valueOf(nextParsed[i]);
                }
                scnr.close();
                fileReader.close();
            }
            else {
                return new int[] {0, 1, 15, 15, 15, 15, x[0], 0, 0, 0};
            }
            
        } 
        catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        return data;
    }

    
}