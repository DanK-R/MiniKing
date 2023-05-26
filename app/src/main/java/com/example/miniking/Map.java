package com.example.miniking;

class Map {
    private static String[] map = {
        "###################################################################",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "###################################################################",
        "#                     #                     #                     #",
        "#                     #         @@          #                     #",
        "#                     #          @          #                     #",
        "#                     #       @@@@@@@       #                     #",
        "#                     #    @@@       @@@    #                     #",
        "#                     #   @      @      @   #                     #",
        "#                     #   @    @   @    @   #                     #",
        "#                     #   @@@@@@@@@@@@@@@   #                     #",
        "#                     #     The Kingdom     #                     #",
        "###################################################################",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
        "#                     #                     #                     #",
    };

    private static String[] rival = {
        "#                     #",
        "#       &&   &&       #",
        "#         & &         #",
        "#       &&&&&&&       #",
        "#    &&&       &&&    #",
        "#   &      &      &   #",
        "#   &    &   &    &   #",
        "#   &&&&&&&&&&&&&&&   #",
        "#                     #",
    };
    //first seed index is map placement
    //123
    //8 4
    //765
    
    public static void mapView(Resources res) {
        //DrawScene.clear();
        
        String[] mapT = map;
        int rivalDat = res.getSeedIndex(0);
        
        //add rival name under the art here
        rival[8] = Printer.Middle(res.getRivalName(), 21);
        //
        int startPointY;
        int startPointX;
        //get y
        if(rivalDat < 4) {
            startPointY = 1;
        }
        else if(rivalDat == 4 || rivalDat == 8) {
            startPointY = 11;
        }
        else {
            startPointY = 21;
        }
        //get x 
        if(rivalDat == 1 || rivalDat == 8 || rivalDat == 7) {
            startPointX = 0;
        }
        else if(rivalDat == 2 || rivalDat == 6) {
            startPointX = 22;
        }
        else {
            startPointX = 44;
        }
        
        for(int i = 0; i < 9; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(mapT[startPointY + i]);
            sb.replace(startPointX, startPointX + 23,rival[i]);
            mapT[startPointY + i] = sb.toString();
        }

        //draw the map
        for(int i = 0; i < map.length; i++) {
            System.out.println(mapT[i]);
        }
        DrawScene.close();
        EnterAsker eAsker = new EnterAsker(false);
    }

    //11
    
}