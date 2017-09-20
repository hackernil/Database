package com.teknas.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn_insert,btn_display;
    EditText edit_name, edit_email;
    DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_display= (Button) findViewById(R.id.btn_display);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_email = (EditText) findViewById(R.id.edit_email);
        db=new DBAdapter(this);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                long id = db.insertContact(
                        edit_name.getText().toString(),
                        edit_email.getText().toString());
                db.close();
                if(id>0){
                    Toast.makeText(MainActivity.this,
                            "Inserted successfully!",Toast.LENGTH_LONG)
                            .show();
                }else{
                    Toast.makeText(MainActivity.this,"Not inserted!",Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                Cursor c = db.getAllContacts();
                if(c.moveToFirst()) {
                    do {
                        Toast.makeText(MainActivity.this,
                                "id : " + c.getString(0) + " name : " +
                                        c.getString(1) + " email : " +
                                        c.getString(2), Toast.LENGTH_LONG).show();
                    }while (c.moveToNext());
                }
                db.close();
            }
        });





    }
}
