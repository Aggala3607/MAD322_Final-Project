package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.covidfight.R;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;
import com.covidfight.model.Info;
import com.covidfight.model.UserNotificationsAdapter;
import com.covidfight.model.WorldAdapter;
import com.covidfight.model.WorldData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorldWiseAPIActivity extends AppCompatActivity {

    List<WorldData> info;
    ListView world_list;
    ProgressDialog p;
    Button btnaddnotification;
    EditText search;
    WorldAdapter adapter;
    String country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_wise_apiactivity);
        getSupportActionBar().setTitle(" World Wise Data");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search=(EditText)findViewById(R.id.searchcountry);

        world_list=(ListView)findViewById(R.id.world_list);
        info = new ArrayList<>();

        getworlddata();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                country = search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.searchCountryWiseData(country);
                // Toast.makeText(ManageStudentsActivity.this, text, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });


    }


    public void getworlddata() {
        p = new ProgressDialog(WorldWiseAPIActivity.this);
        p.setMessage("Loading....");
        p.show();
        ApiService service = new Retrofit.Builder()
                .baseUrl("https://api.apify.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        Call<List<WorldData>> call = service.getData();
        call.enqueue(new Callback<List<WorldData>>() {
            @Override
            public void onResponse(Call<List<WorldData>> call, Response<List<WorldData>> response) {
                p.dismiss();
                // Toast.makeText(AdminTrvelActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(WorldWiseAPIActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    info = response.body();
                    adapter=new WorldAdapter(info, WorldWiseAPIActivity.this);
                    world_list.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<WorldData>> call, Throwable t) {
                p.dismiss();
                Toast.makeText(WorldWiseAPIActivity.this, "Please contact admin !", Toast.LENGTH_SHORT).show();
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