package com.test.notes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    static ArrayList<String> mDataset;
    static Context context;
    int[] arr={R.drawable.notebox1,R.drawable.notebox2};
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textview;
        public ImageView imageView;
        public MyViewHolder(View v){
            super(v);
            //imageView=v.findViewById(R.id.imageView);
            textview= v.findViewById(R.id.filename);
            v.setClickable(true);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent=new Intent(context,newnote.class);
            intent.putExtra("mode","view");
            int pos=getAdapterPosition();
            Log.d("ONCLICK","Click happened at : "+pos);
            intent.putExtra("filename",mDataset.get(pos));
            context.startActivity(intent);
        }
    }
    public ListAdapter(Context context,ArrayList<String> myDataset) {
        this.context=context;
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
    public void deleteItem(int position,String title)
    {
        mDataset.remove(position);
        notifyItemRemoved(position);
        File file=new File(context.getFilesDir(),title);
        if(context.deleteFile(title))
        {
            Toast toast=Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public int getItemCount(){
        return mDataset.size(); }
}

