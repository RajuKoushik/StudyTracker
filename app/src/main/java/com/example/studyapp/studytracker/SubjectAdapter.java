package com.example.studyapp.studytracker;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.BeanHolder> {

    private List<Subject> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnNoteItemClick onNoteItemClick;

    public SubjectAdapter(List<Subject> list,Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }


    @Override
    public BeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item,parent,false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: "+ list.get(position));
        holder.textViewTitle.setText("Subject studied: "+list.get(position).getSubjectName());
        holder.textViewContent.setText("Location: "+list.get(position).getSubjectLocation());
        holder.textViewHours.setText("Hours Studied: "+list.get(position).getSubjectHours());
        holder.textViewTopic.setText("Topic Studied: "+list.get(position).getSubjectTopic());
        holder.textViewDate.setText("Date Studied: "+list.get(position).getSubjectDate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewContent;
        TextView textViewTitle;
        TextView textViewHours;
        TextView textViewTopic;
        TextView textViewDate;

        public BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            textViewHours = itemView.findViewById(R.id.item_hours);
            textViewTopic = itemView.findViewById(R.id.item_topic);
            textViewDate = itemView.findViewById(R.id.item_date);


        }

        @Override
        public void onClick(View view) {
            onNoteItemClick.onNoteClick(getAdapterPosition());


        }
    }

    public interface OnNoteItemClick{
        void onNoteClick(int pos);

    }
}
