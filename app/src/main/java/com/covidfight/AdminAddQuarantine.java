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

public class AdminAddQuarantine extends AppCompatActivity {

        EditText guideline_title,guideline_desc;
        Button addguideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_quarantine);

        guideline_title = findViewById(R.id.guideline_title);
        guideline_desc = findViewById(R.id.guideline_desc);
        addguideline = findViewById(R.id.addguideline);

        getSupportActionBar().setTitle("Add Travel Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addguideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(guideline_title.getText().toString().isEmpty()){
                    Toast.makeText(AdminAddQuarantine.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(guideline_desc.getText().toString().isEmpty()){
                    Toast.makeText(AdminAddQuarantine.this, "Enter Description", Toast.LENGTH_SHORT).show();
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
        String title = guideline_title.getText().toString();
        String description = guideline_desc.getText().toString();
        p = new ProgressDialog(AdminAddQuarantine.this);
        p.setMessage("Adding please wait");
        p.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.quarantine(title,description);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(AdminAddQuarantine.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminAddQuarantine.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(AdminAddQuarantine.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(AdminAddQuarantine.this, t.getMessage(), Toast.LENGTH_LONG).show();
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