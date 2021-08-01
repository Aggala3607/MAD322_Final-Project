package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.covidfight.R;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.AdminInfoAdapter;
import com.covidfight.model.Info;
import com.covidfight.model.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminQuarantineGuidelines extends AppCompatActivity {

    List<Info> info;
    Button btnaddquan;
    ListView quanantine_list;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_quarantine_guidelines);
        btnaddquan=(Button)findViewById(R.id.btnaddquan);

        getSupportActionBar().setTitle("Quarantine Guidelines");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        quanantine_list=(ListView)findViewById(R.id.quanantine_list);


        btnaddquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AdminAddQuarantine.class);
                startActivity(i);
            }
        });
        info = new ArrayList<>();
        getQuarantineGuidelines();
    }

    public void getQuarantineGuidelines() {
        p = new ProgressDialog(AdminQuarantineGuidelines.this);
        p.setMessage("Loading....");
        p.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Info>> call = service.getquarantineinfo();
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                p.dismiss();
                //  Toast.makeText(AdminQuarantineGuidelines.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(AdminQuarantineGuidelines.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    info = response.body();
                    quanantine_list.setAdapter(new AdminInfoAdapter(info, AdminQuarantineGuidelines.this));
                }
            }
            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                p.dismiss();
                Toast.makeText(AdminQuarantineGuidelines.this, "Please contact admin !", Toast.LENGTH_SHORT).show();
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