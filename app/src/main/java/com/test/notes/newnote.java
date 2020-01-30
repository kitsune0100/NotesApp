package com.test.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class newnote extends AppCompatActivity {
    //boolean staus_newnote[]={false,false};
    StringBuilder text = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote);

    }
    @Override
    public  void onResume()
    {
        super.onResume();
        final EditText content=findViewById(R.id.textofnote);
        final EditText titletobeapplied=findViewById(R.id.titleofnote);
        content.setMovementMethod(new ScrollingMovementMethod());
        Button savebutton=findViewById(R.id.save);
        Intent intent=getIntent();
        if(intent.getStringExtra("mode").equals("new")) {
            savebutton.setVisibility(View.VISIBLE);
            savebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        FileOutputStream fos=openFileOutput(titletobeapplied.getText().toString(),MODE_PRIVATE);
                        fos.write((content.getText().toString()).getBytes());
                        fos.close();
                    } catch (IOException e) {
                        Log.e("Exception", "File write failed: " + titletobeapplied);
                        e.printStackTrace();
                    }
                    Intent response = new Intent();
                    response.putExtra("newnote", titletobeapplied.getText().toString());
                    setResult(Activity.RESULT_OK, response);
                    finish();
                }
            });
        }
        else
        {
            setContentView(R.layout.activity_newnote1);
            final TextView content1=findViewById(R.id.textofnote1);
            final TextView titletobeapplied1=findViewById(R.id.titleofnote1);
            Button savebutton1=findViewById(R.id.save1);
            content1.setClickable(false);
            titletobeapplied1.setClickable(false);
            titletobeapplied1.setLongClickable(true);
            String filename=getFilesDir().toString()+"/"+getIntent().getStringExtra("filename");
            File file = new File(filename);

//Read text from file

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                }
            }
            catch (IOException e) {
            }
            titletobeapplied1.setText(file.getName());
            content1.setText(text.toString());
            savebutton1.setVisibility(View.INVISIBLE);

        }
    }
}
