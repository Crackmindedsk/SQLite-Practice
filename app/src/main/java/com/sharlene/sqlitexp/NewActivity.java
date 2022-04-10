package com.sharlene.sqlitexp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class NewActivity extends AppCompatActivity {
    Button logout, update, delete;
    EditText email, password;
    MySqliteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        email=findViewById(R.id.editext_email);
        password=findViewById(R.id.editext_password);
        logout = findViewById(R.id.logout);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);

        helper = new MySqliteOpenHelper(getApplicationContext());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_edittxt=email.getText().toString();
                String password_edittxt=password.getText().toString();
                helper.updateRecord(email_edittxt,password_edittxt);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_edittxt=email.getText().toString();
                helper.deleteRecord(email_edittxt);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}