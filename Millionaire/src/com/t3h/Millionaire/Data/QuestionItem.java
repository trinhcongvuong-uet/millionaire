package com.t3h.Millionaire.Data;

/**
 * Created by Wrongway on 11/01/2016.
 */
public class QuestionItem {
    private int _id;
    private String question;
    private String caseA;
    private String caseB;
    private String caseC;
    private String caseD;
    private int caseTrue;

    public QuestionItem(int id, String question, String caseA, String caseB, String caseC, String caseD, int caseTrue) {
        this._id = id;
        this.question = question;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.caseTrue = caseTrue;
    }

    public int get_id() {
        return _id;
    }

    public int getCaseTrue() {
        return caseTrue;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getQuestion() {
        return question;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseD() {
        return caseD;
    }

}