package com.example.alab.lequizapp.myquizzes;

public class MyQuizzes {

    String quizTitle, quizDesc, accessCode;
    int room_id, quiz_id, totalScore;


    public MyQuizzes(String quizTitle, String quizDesc, String accessCode, int room_id, int quiz_id, int totalScore) {
        this.quizTitle = quizTitle;
        this.quizDesc = quizDesc;
        this.accessCode = accessCode;
        this.room_id = room_id;
        this.quiz_id = quiz_id;
        this.totalScore = totalScore;
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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
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
}
