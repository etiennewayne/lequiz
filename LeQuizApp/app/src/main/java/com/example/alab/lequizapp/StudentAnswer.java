package com.example.alab.lequizapp;

public class StudentAnswer {

    private int question_id;

    private String question;

    private String opt_a;
    private String opt_b;

    private String opt_c;

    private String opt_d;

    private String ans;

    private int equiv_score;


    public StudentAnswer(int question_id, String question, String opt_a, String opt_b, String opt_c, String opt_d, String ans, int equiv_score) {
        this.question_id = question_id;
        this.question = question;
        this.opt_a = opt_a;
        this.opt_b = opt_b;
        this.opt_c = opt_c;
        this.opt_d = opt_d;
        this.ans = ans;
        this.equiv_score = equiv_score;
    }

    public StudentAnswer(){

    }





    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt_a() {
        return opt_a;
    }

    public void setOpt_a(String opt_a) {
        this.opt_a = opt_a;
    }

    public String getOpt_b() {
        return opt_b;
    }

    public void setOpt_b(String opt_b) {
        this.opt_b = opt_b;
    }

    public String getOpt_c() {
        return opt_c;
    }

    public void setOpt_c(String opt_c) {
        this.opt_c = opt_c;
    }

    public String getOpt_d() {
        return opt_d;
    }

    public void setOpt_d(String opt_d) {
        this.opt_d = opt_d;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getEquiv_score() {
        return equiv_score;
    }

    public void setEquiv_score(int equiv_score) {
        this.equiv_score = equiv_score;
    }


}
