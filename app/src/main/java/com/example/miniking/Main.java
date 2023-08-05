//package com.example.miniking;
//
//import android.content.Context;
//
//class Main {
//    private static String menuText = "Good afternoon your Highness, to what course of action does this day have the pleasure?";
//    private static String lossText = "Ahhh Jeeez you lost? Big RIPs dude, wanna try again? ";
//    private Context context;
//    public void main(String[] args) {
//        DrawTitle.go();
//        Asker begin = new Asker("Begin the Game?", false);
//        int[] x = new int[1];
//        if (begin.run()) {
//            ResourceKeeper res = new ResourceKeeper();
//            Questions q = new Questions(res, context);
//
//            begin = new Asker("Continue Previous Game?", false);
//            if (begin.run()) {
//                /*
//                ContinueAsker cont = new ContinueAsker("Enter Password, or cancel.", false);
//                if(cont.run(x)) {
//                    int[] saveData = Load.load(x);
//                    res = new Resources(saveData[1], saveData[2], saveData[3], saveData[4], saveData[5], saveData[6]);
//                    q = new Questions(res, saveData[0], bCheck(saveData[7]), bCheck(saveData[8]), bCheck(saveData[9]));
//                }
//                else {
//                    main(args);
//                }
//
//                 */
//            }
//
//            Help h = new Help();
//            boolean gameRunning = true;
//            while(gameRunning) {
//                if(Fail.check(res)) {
//                    //DrawScene.clear();
//                    DrawScene.open();
//                    res.draw();
//                    DrawScene.close();
//                    //handle showing lost text here
//                    Asker asker = new Asker(lossText, false);
//                    if(asker.run()) {
//                        main(args);
//                    }
//                    else{
//                        System.exit(0);
//                    }
//                }
//                MenuAsker menu = new MenuAsker(menuText, q, res, true);
//            }
//        }
//    }
//
//    private static boolean bCheck(int x) {
//        if(x == 1)
//            return true;
//        return false;
//    }
//}
