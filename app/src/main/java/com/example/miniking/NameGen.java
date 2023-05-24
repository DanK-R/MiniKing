package com.example.miniking;

import java.util.*;
class NameGen {
    private static String[] rivalList = {
        "Elvendom", "Mistfield", "Glimmerfall", "Dragonhold", "Shadowmere", "Ravenwood", "Silvershire", "Sunsetholm", "Windhaven", "Frostwatch", "Wyvern's Run", "Stormpoint", "Moonvale", "Starcrest", "Sylvanwood", "Rivendell", "Mystwick", "Eldrida", "Thornkeep", "Frosthold", "Dagmarsh", "Nethergrove", "Hawthorne", "Whisperwood", "Evermoor"
    };

    //return a random rival name
    public static String rivalName(Random rand) {
        return rivalList[rand.nextInt(rivalList.length - 1)];
    }
}