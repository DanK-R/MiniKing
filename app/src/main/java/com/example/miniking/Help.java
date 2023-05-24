package com.example.miniking;

class Help {
    private static Resources res;
    public Help(Resources res) {
        this.res = res;
        helpView();
    }
    public Help() {
        helpView();
    }
    
    public static void helpView() {
        DrawScene.clear();
        if(res != null) {
            DrawScene.open();
            res.draw();
            DrawScene.close();
        }
        
        DrawScene.open();
        if(res == null) {
            Printer.printyBox("Hello and welcome to the game!");
        }
        Printer.printyBox(
            "The premise is simple, your job as Monarch is to balance the resources of your Kingdom. If any of the resources (Order, Food, Gold, Might) drop to empty, or fill up all the way, you lose. This represents the Kingdoms balance."
        );
        Printer.printyBox("");
        Printer.printyBox("Order: How the citizens at large feel about your reign.");
        Printer.printyBox("");
        Printer.printyBox("Food: How much grain, produce, and livestock there are to feed the people.");
        Printer.printyBox("");
        Printer.printyBox("Gold: The wealth of the Kingdom, but more specifically the wealth of the court.");
        Printer.printyBox("");
        Printer.printyBox("Might: The strength of your army, and the power of it's Generals.");
        Printer.printyBox("");
        Printer.printyBox(
            "Do not worry, the Monarch's job is easy, just answer one of the Kingdom's ultimatums each year. Your court can handle the rest. If you can serve for 45 years, and keep the Kingdom balanced, you win! Pay attention to the ultimatums of your people, carelessness will get you killed."
        );
        
        DrawScene.close();
        EnterAsker eAsker = new EnterAsker(false);
    }
}