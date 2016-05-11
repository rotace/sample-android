package com.example.yasunori.tutorialmemoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    String mFileName = "";
    boolean mNotSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText eTextTitle = (EditText)findViewById(R.id.eTextTitle);
        EditText eTextContent = (EditText)findViewById(R.id.eTextContent);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        if(name != null) {
            mFileName = name;
            eTextTitle.setText(intent.getStringExtra("TITLE"));
            eTextContent.setText(intent.getStringExtra("CONTENT"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_del:
                if(!mFileName.isEmpty()) {
                    if(this.deleteFile(mFileName)){
                        Toast.makeText(this, R.string.msg_del, Toast.LENGTH_SHORT).show();
                    }
                }

                mNotSave = true;
                this.finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mNotSave){
            return;
        }

        EditText eTextTitle = (EditText)findViewById(R.id.eTextTitle);
        EditText eTextContent = (EditText)findViewById(R.id.eTextContent);
        String title = eTextTitle.getText().toString();
        String content = eTextContent.getText().toString();

        if(title.isEmpty() && content.isEmpty() ){
            Toast.makeText(this, R.string.msg_destruction, Toast.LENGTH_SHORT).show();
            return;
        }

        if(mFileName.isEmpty()){
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.JAPAN);
            mFileName = sdf.format(date) + ".txt";
        }

        OutputStream out = null;
        PrintWriter writer = null;
        try {
            out = this.openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.println(title);
            writer.print(content);
            writer.close();
            out.close();
        }catch(Exception e){
            Toast.makeText(this, "File save error!", Toast.LENGTH_LONG).show();
        }
    }

}
