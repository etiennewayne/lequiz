package com.example.alab.lequizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {
    List<Question> questionList;


    public interface ButtonsClickListener {

        void editClick(View v, int position);

        void deleteClick(View v, int position);
    }

    final private ButtonsClickListener mOnClickListener;

    public class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView lblQuestionContent;
        public Button btnEdit, btnDelete;

        public ViewHolder(View itemView){
            super(itemView);

            lblQuestionContent = (TextView) itemView.findViewById(R.id.item_questionContent);

            btnEdit = (Button) itemView.findViewById(R.id.btn_question_edit);
            btnDelete = (Button) itemView.findViewById(R.id.btn_question_delete);


            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.editClick(view, getAdapterPosition());
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.deleteClick(view, getAdapterPosition());
                }
            });

        }

    }

    public QuestionRecyclerAdapter(List<Question> questionList, ButtonsClickListener mOnClickListener){
        this.questionList = questionList;
        this.mOnClickListener = mOnClickListener;
    }


    @Override
    public QuestionRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater infalter = LayoutInflater.from(context);

        View questionView = infalter.inflate(R.layout.item_question, parent, false);

        QuestionRecyclerAdapter.ViewHolder viewHolder = new QuestionRecyclerAdapter.ViewHolder(questionView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionRecyclerAdapter.ViewHolder holder, int position) {
        Question question = questionList.get(position);

        TextView txtvwQuestion = holder.lblQuestionContent;
        txtvwQuestion.setText(question.getQuestion());


    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }





}
