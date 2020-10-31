package com.example.alab.lequizapp;

public class StudentQuizzes {

    String username, lname, fname, mname;
    String room, room_desc, access_code;
    String quizTitle, quizDesc;
    int user_id, room_id, quiz_id, totalScore;

    public StudentQuizzes(String username, String lname, String fname, String mname, String quizTitle, String quizDesc, String room, String room_desc, String access_code, int user_id, int room_id, int quiz_id, int totalScore) {
        this.username = username;
        this.lname = lname;
        this.fname = fname;
        this.mname = mname;
        this.quizTitle = quizTitle;
        this.quizDesc = quizDesc;
        this.room = room;
        this.room_desc = room_desc;
        this.access_code = access_code;
        this.user_id = user_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom_desc() {
        return room_desc;
    }

    public void setRoom_desc(String room_desc) {
        this.room_desc = room_desc;
    }

    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
