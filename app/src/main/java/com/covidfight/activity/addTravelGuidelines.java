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

public class addTravelGuidelines extends AppCompatActivity {

    EditText travel_title, travel_desc;
    Button btn_travel_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_guidelines);

        getSupportActionBar().setTitle("Add Travel Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        travel_title = findViewById(R.id.travel_title);
        travel_desc = findViewById(R.id.travel_desc);

        btn_travel_guide = findViewById(R.id.btn_travel_guide);

        btn_travel_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(travel_title.getText().toString().isEmpty()){
                    Toast.makeText(addTravelGuidelines.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(travel_desc.getText().toString().isEmpty()){
                    Toast.makeText(addTravelGuidelines.this, "Enter Description", Toast.LENGTH_SHORT).show();
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
        String title = travel_title.getText().toString();
        String description = travel_desc.getText().toString();
        p = new ProgressDialog(addTravelGuidelines.this);
        p.setMessage("Adding please wait");
        p.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.travel(title,description);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(addTravelGuidelines.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addTravelGuidelines.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(addTravelGuidelines.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(addTravelGuidelines.this, t.getMessage(), Toast.LENGTH_LONG).show();
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