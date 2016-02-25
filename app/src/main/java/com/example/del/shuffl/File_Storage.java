package com.example.del.shuffl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class File_Storage extends AppCompatActivity {
    EditText nameLoad;
    EditText passwordLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__storage);
        nameLoad = (EditText) findViewById(R.id.showLoadedNameFromFile);
        passwordLoad = (EditText) findViewById(R.id.showLoadedPasseordFromFile);
    }

    public void loadDataFromFile(View v) {
        if (v.getId() == R.id.loadFileData) {
            StringBuffer sb=null;
            FileInputStream fis=null;
            try {
                fis = openFileInput("TestFileStorage.txt");
                int read=-1;
                sb = new StringBuffer();
                while((read=fis.read())!=-1){
                sb.append((char)read);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

         String textName=sb.substring(0,sb.indexOf(" "));
         String textPassword=sb.substring(sb.indexOf(" ")+1);
            nameLoad.setText(textName);
            passwordLoad.setText(textPassword);

        }
        if (v.getId() == R.id.previousFromFile) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
