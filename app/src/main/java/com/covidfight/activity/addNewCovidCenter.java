package com.covidfight.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidfight.R;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addNewCovidCenter extends AppCompatActivity {

        EditText covid_center_name, covid_center_address,covid_center_phone,covid_center_city;
        Button btn_add_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_covid_center);
        getSupportActionBar().setTitle("Add Covid Center");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        covid_center_name = findViewById(R.id.covid_center_name);
        covid_center_phone = findViewById(R.id.covid_center_phone);
        covid_center_address = findViewById(R.id.covid_center_address);
        covid_center_city = findViewById(R.id.covid_center_city);

        btn_add_center = findViewById(R.id.btn_add_center);

        btn_add_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(covid_center_name.getText().toString().isEmpty()){
                    Toast.makeText(addNewCovidCenter.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(covid_center_phone.getText().toString().isEmpty()){
                    Toast.makeText(addNewCovidCenter.this, "Enter Phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(covid_center_address.getText().toString().isEmpty()){
                    Toast.makeText(addNewCovidCenter.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(covid_center_city.getText().toString().isEmpty()){
                    Toast.makeText(addNewCovidCenter.this, "Enter City", Toast.LENGTH_SHORT).show();
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
        String name = covid_center_name.getText().toString();
        String city = covid_center_city.getText().toString();
        String phone = covid_center_phone.getText().toString();
        String addess = covid_center_address.getText().toString();
        p = new ProgressDialog(addNewCovidCenter.this);
        p.setMessage("Adding please wait");
        p.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.centers(name,city,phone,addess);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(addNewCovidCenter.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addNewCovidCenter.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(addNewCovidCenter.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(addNewCovidCenter.this, t.getMessage(), Toast.LENGTH_LONG).show();
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