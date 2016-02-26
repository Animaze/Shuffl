package com.example.del.shuffl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cache_External extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cache__external);
        et = (EditText) findViewById(R.id.editTextEnterTextForCacheSdEtc);
    }

    public void uploadDataToCacheOrSd(View v) {
        String data = et.getText().toString();

        if (v.getId() == R.id.buttonInternalCache) {

            File folder = getCacheDir();
            File myFile = new File(folder, "testCache.txt");
            writeData(myFile, data);
        }

        if (v.getId() == R.id.buttonExternalCache) {
            File folder = getExternalCacheDir();
            File myFile = new File(folder, "testExternalCache.txt");
            writeData(myFile, data);
        }

        if (v.getId() == R.id.buttonExternalPublicDirectory) {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder, "externalPublicFileTest.txt");
            writeData(myFile, data);

        }
        if (v.getId() == R.id.buttonExternalPrivateDirectory) {
            File folder = getExternalFilesDir("PrivateDataShuffl");
            File myFile = new File(folder, "externalPrivateFileTest.txt");
            writeData(myFile, data);
        }

        if (v.getId() == R.id.moveToLoadDataFromCacheOrSd) {
            Intent intent = new Intent(this, Load_Data_From_Cache_External.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.previousFromCacheExternalFile) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void writeData(File myFile, String data) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myFile);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
