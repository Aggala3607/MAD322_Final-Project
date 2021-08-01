package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.covidfight.R;
import com.covidfight.Utils;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_sign_up;
    TextView tv_forget_pass;
    EditText et_email, et_password;
    Button login_btn,login_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        getSupportActionBar().setTitle("User Login");
       /* getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_sign_up = (TextView) findViewById(R.id.tv_sign_up);
        tv_forget_pass = (TextView) findViewById(R.id.tv_forget_pass);
        login_admin=(Button)findViewById(R.id.login_admin);
        login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this, AdminLoginActivity.class));
            }
        });
        login_btn = (Button) findViewById(R.id.login_btn);
        tv_sign_up.setOnClickListener(this);
        tv_forget_pass.setOnClickListener(this);
        login_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tv_sign_up) {
            startActivity(new Intent(UserLoginActivity.this, RegisterActivity.class));
        } else if (v == tv_forget_pass) {
            startActivity(new Intent(UserLoginActivity.this, ForgotpasswordActivity.class));

        } else if (v == login_btn) {

            if(et_email.getText().toString().isEmpty()){
                Toast.makeText(UserLoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(et_password.getText().toString().isEmpty()){
                Toast.makeText(UserLoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }
            loginFunction();

        }

    }
    ProgressDialog p;
    public  void loginFunction() {
        p= new ProgressDialog(UserLoginActivity.this);
        p.setTitle("Login Please wait");
        p.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.user_login(et_email.getText().toString(),et_password.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",et_email.getText().toString());
                    et.commit();
                    startActivity(new Intent(UserLoginActivity.this, UserDashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(UserLoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(UserLoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}