package com.example.miniking;

import android.widget.TextView;

import java.util.*;

class DrawScene {
    private static String[] array = {
            "#####################################################",
            "#####################################################", };

    public static void open() {
        System.out.println("#####################################################");
    }

    public static void close() {
       System.out.println("#####################################################");
    }

    public static void open(TextView display) {
        display.append("#####################################################");
    }

    public static void close(TextView display) {
        display.append("#####################################################");
    }

}
