package com.example.miniking;

class Duty {
    public static void goQuestion(Resources res, Questions q) {
            
        Asker asker = new Asker(q.getNext(), res, true);
        if(asker.run()) {
            //outcome1
            EnterAsker eAsker = new EnterAsker(q.outcome1(), true);
            res.passTime();
        }
        else {
            //outcome2
            EnterAsker eAsker = new EnterAsker(q.outcome2(), true);
            res.passTime();
        }
    }
}
