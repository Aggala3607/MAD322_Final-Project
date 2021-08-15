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
import com.covidfight.model.Info;
import com.covidfight.model.NewsAdapter;
import com.covidfight.model.NotificationsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNewsActivity extends AppCompatActivity {
    List<Info> info;
    ListView news_list;
    ProgressDialog p;
    Button btnaddnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_news);

        getSupportActionBar().setTitle("News Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        news_list=(ListView)findViewById(R.id.news_list);

        btnaddnews=(Button)findViewById(R.id.btnaddnews);
        btnaddnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),adminAddNews.class);
                startActivity(i);
            }
        });
        info = new ArrayList<>();
        getNotifications();
    }


    public void getNotifications() {
        p = new ProgressDialog(AdminNewsActivity.this);
        p.setMessage("Loading....");
        p.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Info>> call = service.getnewsinfo();
        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                p.dismiss();
                // Toast.makeText(AdminTrvelActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(AdminNewsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    info = response.body();
                    news_list.setAdapter(new NewsAdapter(info, AdminNewsActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {
                p.dismiss();
                Toast.makeText(AdminNewsActivity.this, "Please contact admin !", Toast.LENGTH_SHORT).show();
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