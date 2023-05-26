package com.example.miniking;

import android.widget.TextView;

import java.util.*;

class DrawTitle {
    private static String[] array = {
            "#####################################################",
            "#                                                   #",
            "#                #   #  #         #                 #",
            "#                ## ##      ###                     #",
            "#                # # #  #  #   #  #                 #",
            "#                #   #  #  #   #  #                 #",
            "#                #   #  #  #   #  #                 #",
            "#                                                   #",
            "#                                                   #",
            "#                                                   #",
            "#                                                   #",
            "#    #   #  #                    #                  #",
            "#    # ##       ###    ####      #   ###    # #     #",
            "#    ##     #  #   #  #   #   ####  #   #  # # #    #",
            "#    # ##   #  #   #   ####  #   #  #   #  # # #    #",
            "#    #   #  #  #   #      #   ####   ###   # # #    #",
            "#                     ####                          #",
            "#                                                   #",
            "#####################################################",
            "#                                    #  #### #   #  #",
            "#                                    #  #   ## ##   #",
            "#                                    #  #    ##     #",
            "#                                    #  #   ## ##   #",
            "#                                    #  #### #   #  #"};

    public static void go() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static void go(TextView display) {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        display.setText("");
        for (int i = 0; i < array.length; i++) {
            //System.out.println(array[i];
            display.append(array[i]);
            display.append("\n");
        }
    }
}
