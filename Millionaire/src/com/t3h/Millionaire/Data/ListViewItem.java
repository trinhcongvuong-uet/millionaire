package com.t3h.Millionaire.Data;

/**
 * Created by Wrongway on 13/01/2016.
 */
public class ListViewItem {
    private String numberQuestion;
    private String moneyLandmark;

    public ListViewItem(String numberQuestion,String moneyLandmark){
        this.numberQuestion = numberQuestion;
        this.moneyLandmark = moneyLandmark;
    }

    public String getNumberQuestion() {
        return numberQuestion;
    }

    public String getMoneyLandmark() {
        return moneyLandmark;
    }
}
