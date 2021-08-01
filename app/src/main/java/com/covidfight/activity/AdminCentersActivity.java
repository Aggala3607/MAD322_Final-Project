package com.covidfight.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidfight.R;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.CentersAdapter;
import com.covidfight.model.Info;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCentersActivity extends AppCompatActivity {

    List<Info> info;
    ListView centers_list;
    ProgressDialog p;
    Button btnaddcenters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_centers);

        getSupportActionBar().setTitle("Covid Centers");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        centers_list=(ListView)findViewById(R.id.centers_list);

        btnaddcenters=(Button)findViewById(R.id.btnaddcenters);
        btnaddcenters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addNewCovidCenter.class);
                startActivity(i);
            }
        });
        info = new ArrayList<>();
        getCovidCenters();
    }


    public void getCovidCenters() {
        p = new ProgressDialog(AdminCentersActivity.this);
        p.setMessage("Loading....");
        p.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Info>> call = service.getcenters();
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                p.dismiss();
                // Toast.makeText(AdminTrvelActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(AdminCentersActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    info = response.body();
                    centers_list.setAdapter(new CentersAdapter(info, AdminCentersActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                p.dismiss();
                Toast.makeText(AdminCentersActivity.this, "Please contact admin !", Toast.LENGTH_SHORT).show();
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