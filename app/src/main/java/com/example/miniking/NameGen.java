package com.example.miniking;

import java.util.*;
class NameGen {
    private static final String[] rivalList = {
        "Elvendom", "Mistfield", "Glimmerfall", "Dragonhold", "Shadowmere", "Ravenwood", "Silvershire", "Sunsetholm", "Windhaven", "Frostwatch", "Wyvern's Run", "Stormpoint", "Moonvale", "Starcrest", "Sylvanwood", "Rivendell", "Mystwick", "Eldrida", "Thornkeep", "Frosthold", "Dagmarsh", "Nethergrove", "Hawthorne", "Whisperwood", "Evermoor"
    };

    private static final String[] religionList = {
            "Zelorism",
            "Astronism",
            "Caelinism",
            "Nimblism",
            "Elysianism",
            "Zaranthism",
            "Luminism",
            "Thalirism",
            "Zenthorism",
            "Mirabellianism",
            "Faelorianism",
            "Zarixism",
            "Oberanism",
            "Celestianism",
            "Zirellism",
            "Pendragorism",
            "Auronism",
            "Xandorism",
            "Vesperanism",
            "Eldrosism",
            "Zarionism",
            "Nocturnism",
            "Zarissaism",
            "Ignarianism",
            "Elowynism",
            "Nyxianism",
            "Thornhelmism",
            "Zeraphinism",
            "Arcanisism",
            "Galadrianism",
            "Valerianism",
            "Zephyrianism",
            "Oblivianism",
            "Zarionism"
    };

    private static final String[] magicianList = {
            "Zarathorn",
            "Lyndoria",
            "Caldris",
            "Nimblefizz",
            "Eldoria",
            "Zandar",
            "Merlinius",
            "Thalindra",
            "Zalon",
            "Mystara",
            "Faelric",
            "Zarina",
            "Oscarius",
            "Aurorin",
            "Zindra",
            "Gandorin",
            "Aurelia",
            "Xandor",
            "Velara",
            "Eldron",
            "Zorinna",
            "Nocturnis",
            "Zarissa",
            "Ignarius",
            "Elowyn",
            "Nyxia",
            "Thornhelm",
            "Zeraphine",
            "Arcanis",
            "Galadriel",
            "Valerius",
            "Zephyria",
            "Oblivius",
            "Zarion"
    };

    //return a random rival name
    public static String rivalName(Random rand) {
        return rivalList[rand.nextInt(rivalList.length - 1)];
    }

    //return a random religion name
    public static String religionName(Random rand) {
        return religionList[rand.nextInt(religionList.length - 1)];
    }

    //return a random magician name
    public static String magicianName(Random rand) {
        return magicianList[rand.nextInt(magicianList.length - 1)];
    }
}