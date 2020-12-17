package com.example.alab.lequizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentAnswer implements Parcelable {

    private int question_id;

    private String question;

    private String opt_a;

    private String opt_b;

    private String opt_c;

    private String opt_d;

    private String user_ans;

    private String user_ans_desc;

    private String ans;

    private String ans_desc;

    private int equiv_score;


    public StudentAnswer(int question_id, String question, String opt_a, String opt_b, String opt_c, String opt_d, String user_ans, String user_ans_desc, String ans, String ans_desc, int equiv_score) {
        this.question_id = question_id;
        this.question = question;
        this.opt_a = opt_a;
        this.opt_b = opt_b;
        this.opt_c = opt_c;
        this.opt_d = opt_d;
        this.user_ans = user_ans;
        this.user_ans_desc = user_ans_desc;
        this.ans = ans;
        this.ans_desc = ans_desc;
        this.equiv_score = equiv_score;
    }

    public StudentAnswer(){

    }


    protected StudentAnswer(Parcel in) {
        question_id = in.readInt();
        question = in.readString();
        opt_a = in.readString();
        opt_b = in.readString();
        opt_c = in.readString();
        opt_d = in.readString();
        user_ans = in.readString();
        user_ans_desc = in.readString();
        ans = in.readString();
        ans_desc = in.readString();
        equiv_score = in.readInt();
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

    public String getUser_ans() {
        return user_ans;
    }

    public void setUser_ans(String user_ans) {
        this.user_ans = user_ans;
    }

    public String getUser_ans_desc(){
        return user_ans_desc   ;
    }
    public void setUser_ans_desc(String user_ans_desc){
        this.user_ans_desc = user_ans_desc;
    }

    public String getAns() {
        return ans;
    }

    public String getAns_desc(){
        return ans_desc;
    }
    public void setAns_desc(String ans_desc){
        this.ans_desc = ans_desc;
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


    public static final Creator<StudentAnswer> CREATOR = new Creator<StudentAnswer>() {
        @Override
        public StudentAnswer createFromParcel(Parcel in) {
            return new StudentAnswer(in);
        }

        @Override
        public StudentAnswer[] newArray(int size) {
            return new StudentAnswer[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(question_id);
        parcel.writeString(question);
        parcel.writeString(opt_a);
        parcel.writeString(opt_b);
        parcel.writeString(opt_c);
        parcel.writeString(opt_d);
        parcel.writeString(user_ans);
        parcel.writeString(user_ans_desc);
        parcel.writeString(ans);
        parcel.writeString(ans_desc);
        parcel.writeInt(equiv_score);

    }
}
