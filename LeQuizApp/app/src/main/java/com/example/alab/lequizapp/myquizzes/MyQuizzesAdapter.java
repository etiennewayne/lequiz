package com.example.alab.lequizapp.myquizzes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alab.lequizapp.Category;
import com.example.alab.lequizapp.CategoryRecyclerAdapter;
import com.example.alab.lequizapp.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyQuizzesAdapter extends RecyclerView.Adapter<MyQuizzesAdapter.ViewHolder> {
    List<MyQuizzes> quizList;


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtVwQuizTitle,txtVwAccessCode, txtVwTotalScore;

        public ViewHolder(View itemView) {
            super(itemView);

            txtVwQuizTitle = (TextView)itemView.findViewById(R.id.item_myquiz_quiztitle);
            txtVwAccessCode = (TextView)itemView.findViewById(R.id.item_myquiz_accesscode);
            txtVwTotalScore = (TextView)itemView.findViewById(R.id.item_myquiz_viewscore);

        }
    }

    public MyQuizzesAdapter(List<MyQuizzes> quizList){
        this.quizList = quizList;
    }


    @Override
    public MyQuizzesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater infalter = LayoutInflater.from(context);

        View myView = infalter.inflate(R.layout.item_myquizzes, parent, false);

        MyQuizzesAdapter.ViewHolder viewHolder = new MyQuizzesAdapter.ViewHolder(myView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyQuizzesAdapter.ViewHolder holder, int position) {
        MyQuizzes myQuiz = quizList.get(position);

        TextView vwQuizTitle = holder.txtVwQuizTitle;
        vwQuizTitle.setText("QUIZ TITLE: " + myQuiz.getQuizTitle());

        TextView vwAccessCode = holder.txtVwAccessCode;
        vwAccessCode.setText("ACCESS CODE: " + myQuiz.getAccessCode());

        TextView txtvwDesc = holder.txtVwTotalScore;
        txtvwDesc.setText(String.valueOf(myQuiz.getTotalScore()));
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }


}
