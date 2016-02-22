package com.example.del.shuffl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity  {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        t=(TextView)findViewById(R.id.textToast);

    }
    public void makeToast(View v){
        Toast toast = new Toast(this);
        LayoutInflater li = getLayoutInflater();
        View appearence = li.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.toast_ll));

        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,0);
        toast.setView(appearence);
        toast.show();

    }
}
