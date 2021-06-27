package com.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText et_email, et_password;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        et_email =  findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        login_btn = findViewById(R.id.login_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_email.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Please Enter Username..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_password.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }
}