package com.example.miniking;

import android.widget.TextView;

class DrawTitle {
    private static final String[] array = {
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
    public static void go(TextView display) {
        display.setText("");
        for (String s : array) {
            display.append(s);
            display.append("\n");
        }
    }
}
