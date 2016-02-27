package com.example.del.shuffl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    TextView t;
    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        t = (TextView) findViewById(R.id.textToast);
        name = (EditText) findViewById(R.id.nameEt);
        password = (EditText) findViewById(R.id.passwordEt);

    }

    public void doStuff(View v) {
        Intent intent = null, chooser = null;

        if (v.getId() == R.id.toast) {
            Toast toast = new Toast(this);
            LayoutInflater li = getLayoutInflater();
            View appearence = li.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_ll));

            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setView(appearence);
            toast.show();
        }
        if (v.getId() == R.id.email) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hey , I sent this using app");
            String[] to = {"pradhuman2@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_TEXT, "Cool feature");

            chooser = Intent.createChooser(intent, "Select Which app to use");
            startActivity(chooser);
        }
        if (v.getId() == R.id.imageBt) {
            intent = new Intent(Intent.ACTION_SEND);
            Uri uri = Uri.parse("android.resource//com.example.del.shuffl/drawable/" + R.drawable.friends);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            chooser = Intent.createChooser(intent, "select app");
            startActivity(chooser);

        }

    }

    public void sharedPref(View v) {
        SharedPreferences sf = getSharedPreferences("TestData", MODE_PRIVATE);


        SharedPreferences.Editor editor = sf.edit();
        editor.putString("name", name.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, Shared_pref_test.class);
        startActivity(intent);


    }
    public void fileStorageUpload(View v){
        FileOutputStream fos=null;
        try {
            fos = openFileOutput("TestFileStorage.txt",MODE_PRIVATE);
            String enteredName=name.getText().toString()+" ";
            String enteredPassword=password.getText().toString();
            fos.write(enteredName.getBytes());
            fos.write(enteredPassword.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(fos!=null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(this, File_Storage.class);
        startActivity(intent);

    }
    public void storage(View v){
        Intent intent = new Intent(this, Cache_External.class);
        startActivity(intent);
    }
    /*
    SQLite Database Methods Start here
     */
    public void DbFunctions(View v){
        Intent intent = new Intent(this, Database_Testing.class);
        startActivity(intent);
    }


}
