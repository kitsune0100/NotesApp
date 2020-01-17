package com.test.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makenewnote;
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.pink));
        recyclerView=findViewById(R.id.recyclerView);
        File[] files=getFilesDir().listFiles();
        ArrayList<String> mdataset=new ArrayList<String>(10);
        for(int i=0;i<files.length;i++)
        {
            Log.d("Exception","File name : "+files[i].getName());
            mdataset.add(files[i].getName());
        }
        mAdapter=new ListAdapter(mdataset);
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new SwipeToDelete(mAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        layoutManager=new LinearLayoutManager(this);

        layoutManager.canScrollVertically();
        recyclerView.setLayoutManager(layoutManager);
        makenewnote=findViewById(R.id.createnew);
        makenewnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,newnote.class),1);
            }
        });
    }

     @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            String newnotetitle=data.getStringExtra("newnote");

            boolean isPresent=false;
            File[] files=getFilesDir().listFiles();
            for(int i=0;i<files.length && !isPresent;i++)
            {
                isPresent=newnotetitle.equals(files[i]);
            }
            if(!isPresent) {
                mAdapter.mDataset.add(newnotetitle);
                recyclerView.setAdapter(mAdapter);
            }
        } }
}
