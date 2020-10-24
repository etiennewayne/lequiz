package com.example.alab.lequizapp;

public class Room {

    int roomId, quizId;
    String accessCode, room, roomDesc, category, quizTitle, quizDesc;

    public Room(int roomId, int quizId, String accessCode, String room, String roomDesc, String category, String quizTitle, String quizDesc) {
        this.roomId = roomId;
        this.quizId = quizId;
        this.accessCode = accessCode;
        this.room = room;
        this.roomDesc = roomDesc;
        this.category = category;
        this.quizTitle = quizTitle;
        this.quizDesc = quizDesc;
    }


    public Room(int roomId, String room, String roomDesc) {
        this.roomId = roomId;
        this.room = room;
        this.roomDesc = roomDesc;
    }

    public Room() {

    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
