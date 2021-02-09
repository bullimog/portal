package com.bullimog.portal.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FermentBubbles {
    private ArrayList<LocalDateTime> dateTimes;
    private ArrayList<Integer> bubbles;

    public FermentBubbles(){
        dateTimes = new ArrayList<LocalDateTime>();
        bubbles = new ArrayList<Integer>();
    }

    public FermentBubbles(ArrayList<LocalDateTime> dateTimes, ArrayList<Integer> bubbles){
        this.dateTimes = dateTimes;
        this.bubbles = bubbles;
    }

    public void appendFermentBubbles(Integer bubble){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        bubbles.add(bubble);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Integer> getBubbles(){return bubbles;}
}
