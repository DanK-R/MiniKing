package com.example.miniking;

import android.widget.TextView;

import java.util.Objects;

class Printer {
    private static final int[] NPCs = {
            R.drawable.elder_1,

            R.drawable.aristocrat_01,

            R.drawable.aristocrat_02,

            R.drawable.barkeep,

            R.drawable.captain,

            R.drawable.farmer,

            R.drawable.guard,

            R.drawable.masked_man,

            R.drawable.merchant,

            R.drawable.stranger,

            R.drawable.trader_1,

            R.drawable.villager_01
    };

    public static void printyBox(String output, TextView display) {
        String[] outStrings = output.split(" ");
        StringBuilder sb = new StringBuilder();
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

                display.append(sb + "\n");
                sb = new StringBuilder();
                sb.append("#  ");
            }
            else {
                sb.append(outStrings[i]).append(" ");

                if (i == outStrings.length - 1) {
                    int start = sb.length();
                    for (int k = 0; k < width - start; k++) {
                        sb.append(" ");
                        if (sb.length() == width - 1) {
                            sb.append("#");
                            k++;
                        }
                    }
                    display.append(sb.toString());
                    sb = new StringBuilder();
                }
                i++;
            }
        }
        display.append("\n");
    }

    public static void printyBoxReplacer(String output, ResourceKeeper res, TextView display) {
        String[] outStrings = output.split(" ");
        StringBuilder sb = new StringBuilder();
        int width = 53;

        sb.append("#  ");
        for(int i = 0; i < outStrings.length;) {
            //check for npc
            if(!outStrings[i].equals("setNPC")){
                if (res != null) {
                    if (outStrings[i].equals("religion####")) {
                        outStrings[i] = res.getReligionName();
                    }
                    if (Objects.equals(outStrings[i], "magician####")) {
                        outStrings[i] = res.getMagicianName();
                    }
                    if (Objects.equals(outStrings[i], "rival####")) {
                        outStrings[i] = res.getRivalName();
                    }
                    if (outStrings[i].equals("religion####.")) {
                        outStrings[i] = res.getReligionName() + ".";
                    }
                    if (Objects.equals(outStrings[i], "magician####.")) {
                        outStrings[i] = res.getMagicianName() + ".";
                    }
                    if (Objects.equals(outStrings[i], "rival####.")) {
                        outStrings[i] = res.getRivalName() + ".";
                    }

                }

                if (sb.length() + outStrings[i].length() >= width - 3) {
                    int start = sb.length();
                    for (int k = 0; k < width - start; k++) {
                        sb.append(" ");
                        if (sb.length() == width - 1) {
                            sb.append("#");
                            k++;
                        }
                    }


                    display.append(sb + "\n");
                    sb = new StringBuilder();
                    sb.append("#  ");
                } else {
                    sb.append(outStrings[i]).append(" ");

                    if (i == outStrings.length - 1) {
                        int start = sb.length();
                        for (int k = 0; k < width - start; k++) {
                            sb.append(" ");
                            if (sb.length() == width - 1) {
                                sb.append("#");
                                k++;
                            }
                        }

                        display.append(sb.toString());
                        sb = new StringBuilder();
                    }
                    i++;
                }
            }
            else {
                //set the npcWindow
                i++;
                GameActivity.setNPCView(NPCs[Integer.parseInt(outStrings[i])]);
                i++;
            }
        }
        display.append("\n");
    }

    //width is the amount of chars between the #s
    //prints string in the middle of the game display
    public static String Middle(String output, int width) {
        StringBuilder sb = new StringBuilder();
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