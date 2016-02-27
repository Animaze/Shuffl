package com.example.del.shuffl;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Database_Testing extends AppCompatActivity {
    EditText username;
    EditText password;
    DatabaseAdapter da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database__testing);
        username = (EditText) findViewById(R.id.EtDbUsername);
        password = (EditText) findViewById(R.id.EtDbPassword);
        da= new DatabaseAdapter(this);

    }

    public void addUserToDb(View v) {
        if(v.getId()==R.id.uploadToDb) {
            String usernameDb = username.getText().toString();
            String passwordDb = password.getText().toString();
            long id = da.addUser(usernameDb, passwordDb);
            if (id == -1) {
                Log.d("DB", "Sorry, some internal error occured , unable to add user to Db");
            } else {
                Log.d("DB", "User added successfully");
            }
        }
        if(v.getId()==R.id.loadDataFromDb){
            String data = da.loadData();
            Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        }
    }

}
