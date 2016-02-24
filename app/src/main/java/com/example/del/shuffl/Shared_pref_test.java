package com.example.del.shuffl;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Shared_pref_test extends Activity {

    EditText nameLoad;
    EditText passwordLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_pref_test);
        nameLoad=(EditText)findViewById(R.id.nameEt);
        passwordLoad=(EditText)findViewById(R.id.passwordEt);


    }

    public void loadSharedPref(View v){
        SharedPreferences sf =getSharedPreferences("TestData",MODE_PRIVATE);
        nameLoad.setText(sf.getString("name","default"));
        passwordLoad.setText(sf.getString("password","default"));
    }

}
