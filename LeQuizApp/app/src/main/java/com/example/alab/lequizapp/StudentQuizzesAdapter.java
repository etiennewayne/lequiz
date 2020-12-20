package com.example.alab.lequizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class StudentQuizzesAdapter extends RecyclerView.Adapter<StudentQuizzesAdapter.ViewHolder>{

    List<StudentQuizzes> stdQuizzesList;


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView lblQuizTitle, lblViewFullname, lblViewScore, txtDateTaken;

        public ViewHolder(View itemView) {
            super(itemView);

            lblQuizTitle = itemView.findViewById(R.id.item_stdquiz_quiztitle);
            lblViewFullname = itemView.findViewById(R.id.item_stdquiz_fullName);
            lblViewScore = itemView.findViewById(R.id.item_stdquiz_viewscore);
            txtDateTaken = itemView.findViewById(R.id.item_stdquiz_txtDateTaken);

        }
    }

    public StudentQuizzesAdapter(List<StudentQuizzes> stdQuizzesList){
        this.stdQuizzesList = stdQuizzesList;
    }



    @Override
    public StudentQuizzesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater infalter = LayoutInflater.from(context);

        View studentQuizView = infalter.inflate(R.layout.item_student_quizzes, parent, false);

        StudentQuizzesAdapter.ViewHolder viewHolder = new StudentQuizzesAdapter.ViewHolder(studentQuizView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentQuizzesAdapter.ViewHolder holder, int position) {
        StudentQuizzes stdQuiz = stdQuizzesList.get(position);

        TextView vwQuizTitle = holder.lblQuizTitle;
        vwQuizTitle.setText("QUIZ TITLE: " + stdQuiz.getQuizTitle());

        TextView vwFullnamne = holder.lblViewFullname;
        String lname = stdQuiz.getLname();
        String fname = stdQuiz.getFname();
        String mna   = stdQuiz.getMname();

        vwFullnamne.setText(lname + ", " +fname+ " " + mna  );

        TextView vwTotalScore = holder.lblViewScore;
        vwTotalScore.setText(String.valueOf(stdQuiz.getTotalScore()) + "/" + stdQuiz.getTotalPoints());

        TextView vwDateTaken = holder.txtDateTaken;
        vwDateTaken.setText(stdQuiz.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return stdQuizzesList.size();
    }



}
