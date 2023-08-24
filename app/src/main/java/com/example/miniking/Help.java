package com.example.miniking;

import android.widget.TextView;

class Help {
    public static void helpView(TextView display) {

        DrawScene.open(display);
        Printer.printyBox(
            "The premise is simple, your job as Monarch is to balance the resources of your Kingdom. If any of the resources (Order, Food, Gold, Might) drop to empty, or fill up all the way, you lose. This represents the Kingdoms balance."
        , display);
        Printer.printyBox("", display);
        Printer.printyBox("Order: How the citizens at large feel about your reign.", display);
        Printer.printyBox("", display);
        Printer.printyBox("Food: How much grain, produce, and livestock there are to feed the people.", display);
        Printer.printyBox("", display);
        Printer.printyBox("Gold: The wealth of the Kingdom, but more specifically the wealth of the court.", display);
        Printer.printyBox("", display);
        Printer.printyBox("Might: The strength of your army, and the power of it's Generals.", display);
        Printer.printyBox("", display);
        Printer.printyBox(
            "Do not worry, the Monarch's job is easy, just answer one of the Kingdom's ultimatums each year. Your court can handle the rest. If you can serve for 45 years, and keep the Kingdom balanced, you win! Pay attention to the ultimatums of your people, carelessness will get you killed."
                , display);
        
        DrawScene.open(display);
    }
}