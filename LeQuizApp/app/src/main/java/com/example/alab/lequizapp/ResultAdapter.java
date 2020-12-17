package com.example.alab.lequizapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;


import androidx.recyclerview.widget.RecyclerView;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    //private String[] mDataset;

    private List<StudentAnswer> studentAnswers;


    //interface for onclick listener
    interface ListItemClickListener{
        void onListItemClick(int position);
    }

    final private ListItemClickListener mOnClickListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtQuestion, txtQuestionId, txtUserAnswer, txtCorrectAns, result_txtvwTotalScore;


        public ViewHolder(View itemView){
            super(itemView);

           // txtQuestionId = (TextView)itemView.findViewById(R.id.question_id);
            txtQuestion = (TextView)itemView.findViewById(R.id.question);
            txtUserAnswer = (TextView)itemView.findViewById(R.id.user_answer);
            txtCorrectAns = (TextView)itemView.findViewById(R.id.answer);
           // result_txtvwTotalScore = (TextView)itemView.findViewById(R.id.result_txtvwTotalScore);

            //btnClick = (Button) itemView.findViewById(R.id.item);

            //adding click listener
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }

    public ResultAdapter(List<StudentAnswer> studentAnswers, ListItemClickListener mOnClickListener){
        this.studentAnswers = studentAnswers;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View answersView = inflater.inflate(R.layout.item_answers, parent, false);

        ViewHolder viewHolder = new ViewHolder(answersView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ResultAdapter.ViewHolder viewHolder, int position){
        StudentAnswer stdans= studentAnswers.get(position);


//        TextView resultScore = viewHolder.result_txtvwTotalScore;
//        resultScore.setText("SCORE: ");

        TextView textview = viewHolder.txtQuestion;
        textview.setText(stdans.getQuestion());
        //textview.setTypeface(null, Typeface.BOLD);

//        TextView txtvwQuestion_id = viewHolder.txtQuestionId;
//        txtvwQuestion_id.setText("QUESTION ID : " + String.valueOf(stdans.getQuestion_id()));

        TextView txtvwUserAns = viewHolder.txtUserAnswer;
        String userans = stdans.getUser_ans();
        String userans_desc = stdans.getUser_ans_desc();
        String ans = stdans.getAns();

        txtvwUserAns.setText("YOUR ANSWER : " + userans + " - " + userans_desc);
        if(userans.equalsIgnoreCase(ans)){
            txtvwUserAns.setBackgroundColor(Color.rgb(138, 235, 146));
        }else{
            txtvwUserAns.setBackgroundColor(Color.rgb(245, 112, 98));
        }




        TextView txtvwAns = viewHolder.txtCorrectAns;
        txtvwAns.setText("ANSWER : " + ans + " - " + stdans.getAns_desc());
        txtvwAns.setBackgroundColor(Color.rgb(88, 245, 135));



    }

    @Override
    public  int getItemCount(){
        return studentAnswers.size();
    }


}
