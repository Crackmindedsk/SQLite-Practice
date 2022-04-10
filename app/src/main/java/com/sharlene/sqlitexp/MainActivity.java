package com.sharlene.sqlitexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button register, login;
    MySqliteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MySqliteOpenHelper(getApplicationContext());


        email=findViewById(R.id.editext_email);
        password=findViewById(R.id.pasword);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_edittxt=email.getText().toString();
                String password_edittxt=password.getText().toString();
                helper.insertRecord(email_edittxt,password_edittxt);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=1;
                String email_edittxt=email.getText().toString();
                String password_edittxt=password.getText().toString();
                Cursor cursor = helper.selectRecord();
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        if(email_edittxt.equals(cursor.getString(0)) && password_edittxt.equals(cursor.getString(1)))
                        {
                            flag=0;
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NewActivity.class));
                            finish();
                            break;
                        }
                    }
                }
                if(flag==1){
                        Toast.makeText(getApplicationContext(),"Login Failure",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}