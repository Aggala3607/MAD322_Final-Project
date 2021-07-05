package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.covidfight.R;
import com.covidfight.Utils;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    EditText et_name, et_email, et_phone, et_password;
    Button btnSignUp;
    SharedPreferences sharedPreferences;
    List<Account> accounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);
        getMyAccount();
    }

    public void getMyAccount() {
        sharedPreferences = getApplicationContext().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("user_name", "def-val");
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Account>> call = service.profile(email);
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                accounts = response.body();
                Account a = accounts.get(0);
                et_name.setText(a.getName());
                et_email.setText(a.getEmail());
                et_phone.setText(a.getPhone());
                et_password.setText(a.getUser_key());

            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable error) {

                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
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