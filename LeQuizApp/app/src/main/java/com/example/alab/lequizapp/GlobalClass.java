package com.example.alab.lequizapp;

import android.app.Application;



public class GlobalClass extends Application{


    private int id;
    private String username = "";
    private String lname = "";
    private String fname = "";
    private String mname = "";
    private String position = "";

    private String accessCode = "";

    //for student variable

    private String category;

    private String ayCode;

    private String ay;




    //String ip = "192.168.0.10";


//ws://

    String protocol = "http://";
    String protocolWs = "ws://";

    private String IPAddress ;

    //hold the IP Address kay e overwrite nia ang IP Address for new Setting
    private String tempIPAddress;

    private String WebSocketAddress;

    private int roomId;

    public int quizId;


//    public void setSetting(){
//
//        if(getIPAddress() != null){
//            this.tempIPAddress = getIPAddress();
//            this.IPAddress = protocol + "//" + tempIPAddress;
//            this.WebSocketAddress = protocolWs+ "//" + tempIPAddress + ":8080";
//        }
//    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getLname(){
        return lname;
    }
    public void setLname(String lname){
        this.lname = lname;
    }
    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname = fname;
    }

    public String getMname(){
        return mname;
    }
    public void setMname(String mname){
        this.mname = mname;
    }


    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position=position;
    }

    public String getAccessCode(){return this.accessCode;}
    public void setAccessCode(String accessCode){
        this.accessCode  = accessCode;
    }


    public int getQuizId(){
        return quizId;
    }
    public void setQuizId(int quizId){
        this.quizId = quizId;
    }


    //IPAddress of the Server
    public String getIPAddressShow(){
        return IPAddress;
    }
    public String getIPAddress(){
        return protocol + IPAddress;
    }
    public  void setIPAddress(String IPAddress){
        this.IPAddress = IPAddress;
    }


    //IP Address WebSocket
    public String getWebSocketAddress(){
        return protocolWs + IPAddress + ":8080";
    }
    public  void setWebSocketAddress(String webSocketAddress){
        this.WebSocketAddress = webSocketAddress;
    }


    public void setRoomId(int roomId){
        this.roomId = roomId;
    }
    public int getRoomId(){
        return this.roomId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAyCode() {
        return ayCode;
    }

    public void setAyCode(String ayCode) {
        this.ayCode = ayCode;
    }


    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }
}
