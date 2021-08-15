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

public class EditCenterActivity extends AppCompatActivity {

    EditText covid_center_name, covid_center_address,covid_center_phone,covid_center_city;
    Button btn_update_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_center);
        getSupportActionBar().setTitle("Update Covid Center");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        covid_center_name = findViewById(R.id.covid_center_name);
        covid_center_phone = findViewById(R.id.covid_center_phone);
        covid_center_address = findViewById(R.id.covid_center_address);
        covid_center_city = findViewById(R.id.covid_center_city);


        covid_center_name.setText(getIntent().getStringExtra("name"));
        covid_center_city.setText(getIntent().getStringExtra("city"));
        covid_center_phone.setText(getIntent().getStringExtra("phone"));
        covid_center_address.setText(getIntent().getStringExtra("address"));

        btn_update_center = findViewById(R.id.btn_update_center);

        btn_update_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (covid_center_name.getText().toString().isEmpty()) {
                    Toast.makeText(EditCenterActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                } else if (covid_center_phone.getText().toString().isEmpty()) {
                    Toast.makeText(EditCenterActivity.this, "Enter Phone", Toast.LENGTH_SHORT).show();
                    return;
                } else if (covid_center_address.getText().toString().isEmpty()) {
                    Toast.makeText(EditCenterActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                } else if (covid_center_city.getText().toString().isEmpty()) {
                    Toast.makeText(EditCenterActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    updateCenterData();
                }
            }
        });
    }
    ProgressDialog p;

    private void updateCenterData() {
        String name = covid_center_name.getText().toString();
        String city = covid_center_city.getText().toString();
        String phone = covid_center_phone.getText().toString();
        String addess = covid_center_address.getText().toString();
        String id = getIntent().getStringExtra("id");
        p = new ProgressDialog(EditCenterActivity.this);
        p.setMessage("Adding please wait");
        p.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.editcenter(id,name,city,phone,addess);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(EditCenterActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditCenterActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(EditCenterActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(EditCenterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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