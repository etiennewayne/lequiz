package com.example.alab.lequizapp;

import java.util.ArrayList;

public class Quizes {


    private int quizID;
    private String username, quizTitle, quizDesc;

    public Quizes(){

    }

    public Quizes(int quizID, String username, String quizTitle, String quizDesc) {
        this.quizID = quizID;
        this.username = username;
        this.quizTitle = quizTitle;
        this.quizDesc = quizDesc;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizDesc() {
        return quizDesc;
    }

    public void setQuizDesc(String quizDesc) {
        this.quizDesc = quizDesc;
    }





}
