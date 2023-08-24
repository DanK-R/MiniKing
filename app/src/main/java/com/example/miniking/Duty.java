package com.example.miniking;

import android.widget.TextView;

class Duty {
    private final ResourceKeeper res;
    private final Questions q;
    private final TextView display;
    public Duty(ResourceKeeper res, Questions q, TextView display) {
        this.res = res;
        this.q = q;
        this.display = display;
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
        }
        else {
            //outcome2
            asker = new Asker(q.outcome2(), true, display);

        }
        asker.draw();
        asker.acknowledge();
        res.passTime();
    }

}
