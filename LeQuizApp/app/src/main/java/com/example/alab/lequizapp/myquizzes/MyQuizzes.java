package com.example.alab.lequizapp.myquizzes;

public class MyQuizzes {

    String quizTitle, quizDesc, accessCode;
    int  quiz_id, totalScore, totalPoints;
    String quizDate, courseTitle;


    public MyQuizzes(String quizTitle, String quizDesc, String accessCode, String courseTitle, int quiz_id, int totalScore, String quizDate, int totalPoints) {
        this.quizTitle = quizTitle;
        this.quizDesc = quizDesc;
        this.accessCode = accessCode;
        this.courseTitle = courseTitle;
        this.quiz_id = quiz_id;
        this.totalScore = totalScore;
        this.quizDate = quizDate;
        this.totalPoints = totalPoints;
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


    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(String quizDate) {
        this.quizDate = quizDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
