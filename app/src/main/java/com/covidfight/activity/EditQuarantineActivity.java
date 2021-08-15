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

public class EditQuarantineActivity extends AppCompatActivity {

    EditText guideline_title,guideline_desc;
    Button updateguideline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quarantine);
        getSupportActionBar().setTitle("Update Quarantine Guidelines");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        guideline_title = findViewById(R.id.guideline_title);
        guideline_desc = findViewById(R.id.guideline_desc);
        updateguideline = findViewById(R.id.updateguideline);


        guideline_title.setText(getIntent().getStringExtra("title"));
        guideline_desc.setText(getIntent().getStringExtra("description"));

        updateguideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(guideline_title.getText().toString().isEmpty()){
                    Toast.makeText(EditQuarantineActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(guideline_desc.getText().toString().isEmpty()){
                    Toast.makeText(EditQuarantineActivity.this, "Enter Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    updateQuarantineData();
                }
            }
        });
    }
    ProgressDialog p;

    private void updateQuarantineData() {
        String title = guideline_title.getText().toString();
        String description = guideline_desc.getText().toString();
        String id = getIntent().getStringExtra("id");
        p = new ProgressDialog(EditQuarantineActivity.this);
        p.setMessage("Adding please wait");
        p.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.editquantine(id,title,description);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(EditQuarantineActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditQuarantineActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(EditQuarantineActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(EditQuarantineActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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