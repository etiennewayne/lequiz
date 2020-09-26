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

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtViewID, txtViewTitle, txtViewDesc;
        public Button btnEdit, btnDelete;

        private Context context;

        public ViewHolder(final View itemView){
            super(itemView);

           // nameTextView = (TextView)itemView.findViewById(R.id.contact_name);
           // messageButton = (Button) itemView.findViewById(R.id.message_button);

            txtViewID = (TextView)itemView.findViewById(R.id.txtQuizId);
            txtViewTitle = (TextView)itemView.findViewById(R.id.txtQuizTitle);
            txtViewDesc = (TextView)itemView.findViewById(R.id.txtQuizDesc);

            btnEdit = (Button)itemView.findViewById((R.id.btnQuizEdit));
            btnDelete = (Button)itemView.findViewById(R.id.btnQuizDelete);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });

        }


        @Override
        public void onClick(View v) {
            //Toast.makeText(context, "Edit was click", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Quizes> listQuizes;

    public QuizesAdapter(List<Quizes> listQuizes){
        this.listQuizes = listQuizes;
    }

    @Override
    public QuizesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_quizes, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(QuizesAdapter.ViewHolder viewHolder, int position){
        Quizes quizes = listQuizes.get(position);

        TextView txtViewID = viewHolder.txtViewID;
        TextView txtViewTitle = viewHolder.txtViewTitle;
        TextView txtDesc = viewHolder.txtViewDesc;

        txtViewID.setText("QUIZ ID : " + String.valueOf(quizes.getQuizID()));
        txtViewTitle.setText("QUIZ TITLE : " + quizes.getQuizTitle());
        txtDesc.setText("QUIZ DESCRIPTION : " + quizes.getQuizDesc());

    }

    @Override
    public  int getItemCount(){
        return listQuizes.size();
    }


}
