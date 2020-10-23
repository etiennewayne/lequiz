package com.example.alab.lequizapp;

public class Category {

    public Category(int categoryId, int userId, int academicYerId, String ayCode, String category, String categoryDesc) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.academicYerId = academicYerId;
        this.ayCode = ayCode;
        this.category = category;
        this.categoryDesc = categoryDesc;
    }

    public Category(){

    }

    private int categoryId;

    private int userId;

    private int academicYerId;


    private String ayCode;

    private String category;

    private String categoryDesc;


    public String getAyCode() {
        return ayCode;
    }

    public void setAyCode(String ayCode) {
        this.ayCode = ayCode;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAcademicYerId() {
        return academicYerId;
    }

    public void setAcademicYerId(int academicYerId) {
        this.academicYerId = academicYerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }




}
