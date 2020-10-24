package com.example.alab.lequizapp;

public class Question {

    String question;
    String optA;

    public Question(int questionId, int quizId, String question, String optA, String optB, String optC, String optD, String optAns, int setTime, int equivScore) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.optAns = optAns;
        this.setTime = setTime;
        this.equivScore = equivScore;
    }

    public Question(){

    }

    String optB;
    String optC;
    String optD;
    String optAns;

    int questionId;
    int quizId;
    int setTime;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getOptAns() {
        return optAns;
    }

    public void setOptAns(String optAns) {
        this.optAns = optAns;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getSetTime() {
        return setTime;
    }

    public void setSetTime(int setTime) {
        this.setTime = setTime;
    }

    public int getEquivScore() {
        return equivScore;
    }

    public void setEquivScore(int equivScore) {
        this.equivScore = equivScore;
    }

    int equivScore;



}
