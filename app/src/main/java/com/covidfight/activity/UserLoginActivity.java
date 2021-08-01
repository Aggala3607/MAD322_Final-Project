package com.covidfight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidfight.R;

public class UserLoginActivity extends AppCompatActivity {

    EditText et_email, et_password;
    TextView tv_sign_up, tv_forget_pass;
    Button login_btn, login_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_forget_pass = findViewById(R.id.tv_forget_pass);
        login_admin = findViewById(R.id.login_admin);
        login_btn = findViewById(R.id.login_btn);

        login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this, AdminLoginActivity.class));

            }
        });

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this, RegisterActivity.class));

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_email.getText().toString().isEmpty()){
                    Toast.makeText(UserLoginActivity.this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_password.getText().toString().isEmpty()){
                    Toast.makeText(UserLoginActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }
}