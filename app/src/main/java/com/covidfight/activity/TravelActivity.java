    package com.covidfight.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidfight.R;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.Info;
import com.covidfight.model.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelActivity extends AppCompatActivity {

    List<Info> info;

    ListView travel_list;
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        getSupportActionBar().setTitle("Travel Guidelines");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        travel_list=(ListView)findViewById(R.id.travel_list);

        info = new ArrayList<>();
        getTravelGuidelines();
    }


    public void getTravelGuidelines() {
        p = new ProgressDialog(TravelActivity.this);
        p.setMessage("Loading....");
        p.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Info>> call = service.gettravelinfo();
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                p.dismiss();
              //  Toast.makeText(TravelActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(TravelActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    info = response.body();
                    travel_list.setAdapter(new InfoAdapter(info, TravelActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                p.dismiss();
                Toast.makeText(TravelActivity.this, "Please contact admin !", Toast.LENGTH_SHORT).show();
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