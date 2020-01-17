package com.test.notes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>  {
    ArrayList<String> mDataset;
    int[] arr={R.drawable.notebox1,R.drawable.notebox2};
    public GestureDetectorCompat mDetector;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textview;
        public MyViewHolder(View v){
            super(v);
            textview= v.findViewById(R.id.filename);

        }
    }
    public ListAdapter(ArrayList<String> myDataset) {
        mDataset=myDataset;
    }
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row,parent,false);
        return new MyViewHolder(v);
    }
  public void onBindViewHolder(MyViewHolder holder,int position) {
      holder.textview.setText(mDataset.get(position));
        holder.textview.setBackgroundResource(arr[0]);
    }
    public int getItemCount(){
        return mDataset.size(); }
}

