package com.example.miniking;

import android.widget.TextView;

class Map {
    private static final String[] map = {
        "####################################################",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "####################################################",
        "#                #      @@        #                #",
        "#                #        @       #                #",
        "#                #      @@@@      #                #",
        "#                #    @@    @@    #                #",
        "#                #   @   @@   @   #                #",
        "#                #   @  @  @  @   #                #",
        "#                #   @@@@@@@@@@   #                #",
        "#                #  Your Kingdom  #                #",
        "####################################################",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#                #                #                #",
        "#####################################################\n"
    };

    private static final String[] rival = {
        "#     &&  &&     #",
        "#       &&       #",
        "#      &&&&      #",
        "#    &&    &&    #",
        "#   &   &&   &   #",
        "#   &  &  &  &   #",
        "#   &&&&&&&&&&   #",
        "#                #",
    };
    //first seed index is map placement
    //123
    //8 4
    //765
    
    public static void mapView(ResourceKeeper res, TextView display) {
        
        String[] mapT = map;
        int rivalDat = res.getSeedIndex(0);
        
        //add rival name under the art here
        rival[7] = Printer.Middle(res.getRivalName(), 16);
        //
        int startPointY;
        int startPointX;
        //get y
        if(rivalDat < 4) {
            startPointY = 1;
        }
        else if(rivalDat == 4 || rivalDat == 8) {
            startPointY = 10;
        }
        else {
            startPointY = 19;
        }
        //get x 
        if(rivalDat == 1 || rivalDat == 8 || rivalDat == 7) {
            startPointX = 0;
        }
        else if(rivalDat == 2 || rivalDat == 6) {
            startPointX = 17;
        }
        else {
            startPointX = 34;
        }
        
        for(int i = 0; i < rival.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(mapT[startPointY + i]);
            sb.replace(startPointX, startPointX + 18,rival[i]);
            mapT[startPointY + i] = sb.toString();
        }

        //draw the map
        display.setText("");
        for(int i = 0; i < mapT.length; i++) {
            if(i != 0) {
                display.append("\n");
            }
            display.append(mapT[i]);
        }
    }

    //11
    
}