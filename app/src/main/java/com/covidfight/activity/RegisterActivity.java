package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.covidfight.R;

public class RegisterActivity extends AppCompatActivity {

    TextView et_name, et_email, et_phone, et_password;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name =  findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_email.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_password.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }
}