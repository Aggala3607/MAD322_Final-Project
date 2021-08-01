package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.covidfight.R;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText et_name, et_email, et_phone, et_password;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_email.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_password.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_phone.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Enter Phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    submitData();
                }
            }
        });
    }
    ProgressDialog p;

    private void submitData() {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String mobileno = et_phone.getText().toString();

        p = new ProgressDialog(RegisterActivity.this);
        p.setMessage("Loading....");
        p.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);

        Call<ResponseData> call = service.user_registration(name, email,password, mobileno);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(RegisterActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegisterActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}