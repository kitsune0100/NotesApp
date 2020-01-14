package com.test.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    ArrayList<String> mDataset;
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
    }
    public int getItemCount(){
        return mDataset.size();
    }

}
