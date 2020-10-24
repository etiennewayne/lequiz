package com.example.alab.lequizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import androidx.recyclerview.widget.RecyclerView;

public class QuizesAdapter extends  RecyclerView.Adapter<QuizesAdapter.ViewHolder>{

    List<Quizes> listQuizzes;

    public interface ButtonsClickListener {
        void editClick(View v, int position);

        void deleteClick(View v, int position);

        void questionClick(View v, int position);
    }


    final private ButtonsClickListener mOnClickListener;



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtvwQuizTitle, txtvwCategory, txtvwQuizDesc;
        public Button btnEdit, btnDelete, btnQuestion;



        public ViewHolder(final View itemView){
            super(itemView);

           // nameTextView = (TextView)itemView.findViewById(R.id.contact_name);
           // messageButton = (Button) itemView.findViewById(R.id.message_button);

            txtvwQuizTitle = (TextView)itemView.findViewById(R.id.item_quiz_lbltitle);
            txtvwCategory = (TextView)itemView.findViewById(R.id.item_quiz_lblcategory);
            txtvwQuizDesc = (TextView)itemView.findViewById(R.id.item_quiz_lbldesc);

            btnEdit = (Button)itemView.findViewById((R.id.btn_quiz_edit));
            btnDelete = (Button)itemView.findViewById(R.id.btn_quiz_delete);
            btnQuestion = (Button)itemView.findViewById(R.id.btn_quiz_question);

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

            btnQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.questionClick(view, getAdapterPosition());
                }
            });

        }

    }


    public QuizesAdapter(List<Quizes> listQuizzes, ButtonsClickListener mOnClickListener){
        this.listQuizzes = listQuizzes;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public QuizesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View quizView = inflater.inflate(R.layout.item_quizes, parent, false);

        ViewHolder viewHolder = new ViewHolder(quizView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(QuizesAdapter.ViewHolder viewHolder, int position){
        Quizes quizzes = listQuizzes.get(position);

        TextView vwTitle = viewHolder.txtvwQuizTitle;
        TextView vwCategory = viewHolder.txtvwCategory;
        TextView vwDesc = viewHolder.txtvwQuizDesc;

        vwTitle.setText("QUIZ TITLE : " + quizzes.getQuizTitle());
        vwCategory.setText("CATEGORY : " + quizzes.getCategory());
        vwDesc.setText(quizzes.getQuizDesc());

    }

    @Override
    public  int getItemCount(){
        return listQuizzes.size();
    }


}
