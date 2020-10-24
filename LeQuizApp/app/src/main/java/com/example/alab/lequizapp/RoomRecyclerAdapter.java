package com.example.alab.lequizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.ViewHolder> {

    List<Room> roomList;

    public interface ButtonsClickListener {

        void stdQuizzesClick(View v, int position);

    }

    final private RoomRecyclerAdapter.ButtonsClickListener mOnClickListener;

    public class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView lblRoom, lblAccessCode, lblQuizTitle, lblCategory;
        public Button btnStudentList;

        public ViewHolder(View itemView){
            super(itemView);

            lblRoom = (TextView)itemView.findViewById(R.id.lblRoom);
            lblAccessCode = (TextView)itemView.findViewById(R.id.lblcode);
            lblQuizTitle = (TextView)itemView.findViewById(R.id.lblquiztitle);
            lblCategory = (TextView)itemView.findViewById(R.id.lblcategory);

            btnStudentList = (Button)itemView.findViewById(R.id.btn_student_quizzes);

            btnStudentList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.stdQuizzesClick(view, getAdapterPosition());
                }
            });

        }

    }

    public RoomRecyclerAdapter(List<Room> roomList, RoomRecyclerAdapter.ButtonsClickListener mOnClickListener){
        this.roomList = roomList;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public RoomRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater infalter = LayoutInflater.from(context);

        View roomView = infalter.inflate(R.layout.item_room, parent, false);

        RoomRecyclerAdapter.ViewHolder viewHolder = new RoomRecyclerAdapter.ViewHolder(roomView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RoomRecyclerAdapter.ViewHolder holder, int position) {
        Room room = roomList.get(position);

        TextView txtvwRoom = holder.lblRoom;
        txtvwRoom.setText(room.getRoom());

        TextView txtvwCode = holder.lblAccessCode;
        txtvwCode.setText(room.getAccessCode());

        TextView txtvwQuizTitle = holder.lblQuizTitle;
        txtvwQuizTitle.setText(room.getQuizTitle());

        TextView txtvwCategory = holder.lblCategory;
        txtvwCategory.setText(room.getCategory());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }


}
