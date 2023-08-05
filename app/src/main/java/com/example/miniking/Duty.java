package com.example.miniking;

import android.widget.TextView;

class Duty {
    private ResourceKeeper res;
    private Questions q;
    private TextView display;
    public Duty(ResourceKeeper res, Questions q, TextView display) {
        this.res = res;
        this.q = q;
        this.display = display;
    }

    //depreciated
    public static void goQuestion(ResourceKeeper res, Questions q) {
            
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

    //new standard
    public void nextQuestion() {
        Asker asker = new Asker(q.getNext(), res, true, display);
        asker.draw();
        asker.choice();
    }

    public void outcome(boolean value) {
        Asker asker;
        if(value) {
            asker = new Asker(q.outcome1(), true, display);
            asker.draw();
            asker.acknowledge();
        }
        else {
            //outcome2
            asker = new Asker(q.outcome2(), true, display);
            asker.draw();
            asker.acknowledge();

        }
        res.passTime();
    }

}
