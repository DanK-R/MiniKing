package com.example.miniking;

import java.util.*;

class DrawScene {
    private static String[] array = {
            "###################################################################",
            "###################################################################", };

    public static void open() {
        System.out.println(
            "###################################################################");
    }

    public static void close() {
       System.out.println(
            "###################################################################"
        );
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
