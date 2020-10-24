package com.example.alab.lequizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    List<Category> categoryList;

    //interace for click action
    public interface ButtonsClickListener {

        void editClick(View v, int position);

        void deleteClick(View v, int position);
    }

    final private  ButtonsClickListener mOnClickListener;


    public class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView lblCategory, lblCategoryDesc, lblAY;
        public Button btnEdit, btnDelete;

        public ViewHolder(View itemView){
            super(itemView);

            lblCategory = (TextView)itemView.findViewById(R.id.item_lblCategory);
            lblCategoryDesc = (TextView)itemView.findViewById(R.id.item_CategoryDesc);
            lblAY = (TextView)itemView.findViewById(R.id.item_lblAY);
            btnEdit = (Button) itemView.findViewById(R.id.btnCategoryEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnCategoryDelete);


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

            //itemView.setOnClickListener(this);
        }


    }


    public CategoryRecyclerAdapter(List<Category> categoryList, ButtonsClickListener mOnClickListener){
        this.categoryList = categoryList;
        this.mOnClickListener = mOnClickListener;
    }



    @Override
    public CategoryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater infalter = LayoutInflater.from(context);

        View categoryView = infalter.inflate(R.layout.item_categories, parent, false);

        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryRecyclerAdapter.ViewHolder holder, int position) {


        Category category = categoryList.get(position);

        TextView txtvwAY = holder.lblAY;
        txtvwAY.setText(category.getAyCode());

        TextView txtvwCategory = holder.lblCategory;
        txtvwCategory.setText(category.getCategory());

        TextView txtvwDesc = holder.lblCategoryDesc;
        txtvwDesc.setText(category.getCategoryDesc());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}
