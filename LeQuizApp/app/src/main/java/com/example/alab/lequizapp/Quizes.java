package com.example.alab.lequizapp;

import java.util.ArrayList;

public class Quizes {


    private int quizID;
    private int userId;

    private int categoryId;
    private  String quizTitle, quizDesc, category, accessCode;


    public Quizes(){

    }

    public Quizes(int quizID, int userId, int categoryId, String category, String quizTitle, String quizDesc, String accessCode) {
        this.quizID = quizID;
        this.userId = userId;
        this.categoryId = categoryId;
        this.category = category;
        this.quizTitle = quizTitle;
        this.quizDesc = quizDesc;
        this.accessCode = accessCode;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
