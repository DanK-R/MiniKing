package com.example.miniking;

import android.widget.TextView;

import org.w3c.dom.Text;

class Printer {
    public static void printy(String output) {
        String[] outStrings = output.split(" ");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < outStrings.length;) {
            if(sb.length() + outStrings[i].length() >= 68) {
                System.out.println(sb.toString());
                sb = new StringBuffer();
            }
            else {
                sb.append(outStrings[i] + " ");
                
                if(i == outStrings.length - 1) {
                    System.out.println(sb.toString());
                }
                i++;
            }
        }
    }

    public static void printyBoxShort(String output) {
        StringBuffer sb = new StringBuffer();
        sb.append("#  ");
        sb.append(output);
        int start = sb.length();
        for(int k = 0; k < 67 - start; k++) {
            sb.append(" ");
            if(sb.length() == 66) {
                sb.append("#");
                k++;
            }
        }
        System.out.println(sb.toString());
    }

    public static void printyBox(String output) {
        String[] outStrings = output.split(" ");
        StringBuffer sb = new StringBuffer();

        sb.append("#  ");
        for(int i = 0; i < outStrings.length;) {
            if(sb.length() + outStrings[i].length() >= 64) {
                int start = sb.length();
                for(int k = 0; k < 67 - start; k++) {
                    sb.append(" ");
                    if(sb.length() == 66) {
                        sb.append("#");
                        k++;
                    }
                }
                
                System.out.println(sb.toString());
                sb = new StringBuffer();
                sb.append("#  ");
            }
            else {
                sb.append(outStrings[i] + " ");
                
                if(i == outStrings.length - 1) {
                    int start = sb.length(); 
                    for(int k = 0; k < 67 - start; k++) {
                        sb.append(" ");
                        if(sb.length() == 66) {
                            sb.append("#");
                            k++;
                        }
                    }
                    System.out.println(sb.toString());
                }
                i++;
            }
        }
    }

    public static void printyBox(String output, TextView display) {
        String[] outStrings = output.split(" ");
        StringBuffer sb = new StringBuffer();
        int width = 53;

        sb.append("#  ");
        for(int i = 0; i < outStrings.length;) {
            if(sb.length() + outStrings[i].length() >= width - 3) {
                int start = sb.length();
                for(int k = 0; k < width - start; k++) {
                    sb.append(" ");
                    if(sb.length() == width - 1) {
                        sb.append("#");
                        k++;
                    }
                }

                //System.out.println(sb.toString());
                display.append("\n");
                display.append(sb.toString());
                sb = new StringBuffer();
                sb.append("#  ");
            }
            else {
                sb.append(outStrings[i] + " ");

                if(i == outStrings.length - 1) {
                    int start = sb.length();
                    for(int k = 0; k < width - start; k++) {
                        sb.append(" ");
                        if(sb.length() == width - 1) {
                            sb.append("#");
                            k++;
                        }
                    }
                    //System.out.println(sb.toString());
                    display.append("\n");
                    display.append(sb.toString());
                    sb = new StringBuffer();
                }
                i++;
            }
        }
    }

    //width is the amount of chars between the #s
    //prints string in the middle of the game display
    public static String Middle(String output, int width) {
        StringBuffer sb = new StringBuffer();
        sb.append("#");
        int buffer = (width - output.length()) / 2;
        for(int i = 0; i < buffer; i++) {
            sb.append(" ");
        }
        sb.append(output);
        int start = sb.length();
        for(int k = 0; k < width + 1 - start; k++) {
            sb.append(" ");
            if(sb.length() == width + 1) {
                sb.append("#");
                k++;
            }
        }
        return sb.toString();
    }
}