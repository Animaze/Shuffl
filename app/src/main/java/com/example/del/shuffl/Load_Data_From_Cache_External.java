package com.example.del.shuffl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Load_Data_From_Cache_External extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load__data__from__cache__external);
        et = (EditText) findViewById(R.id.editTextLoadDataFromCacheSdEtc);
    }

    public void loadDataToCacheOrSd(View v) {

        if (v.getId() == R.id.loadButtonInternalCache) {
            File folder = getCacheDir();
            File myFile = new File(folder, "testCache.txt");
            et.setText(readData(myFile));
        }

        if (v.getId() == R.id.loadButtonExternalCache) {
            File folder = getExternalCacheDir();
            File myFile = new File(folder, "testExternalCache.txt");
            et.setText(readData(myFile));
        }

        if (v.getId() == R.id.loadButtonExternalPublicDirectory) {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder, "externalPublicFileTest.txt");
            et.setText(readData(myFile));
        }
        if (v.getId() == R.id.loadButtonExternalPrivateDirectory) {
            File folder = getExternalFilesDir("PrivateDataShuffl");
            File myFile = new File(folder, "externalPrivateFileTest.txt");
            et.setText(readData(myFile));
        }

        if (v.getId() == R.id.previousToCacheExternalFile) {
            Intent intent = new Intent(this, Cache_External.class);
            startActivity(intent);
        }
    }

    String readData(File myData) {
        StringBuffer sb = null;
        sb = new StringBuffer();
        int read = -1;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myData);
            while((read=fis.read())!=-1)sb.append((char)read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
