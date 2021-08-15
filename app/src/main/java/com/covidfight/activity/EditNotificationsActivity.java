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

public class EditNotificationsActivity extends AppCompatActivity {

    EditText notification_title, notification_desc;

    Button btn_update_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notifications);
        getSupportActionBar().setTitle("update Notification");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notification_title = findViewById(R.id.notification_title);
        notification_desc = findViewById(R.id.notificatiaon_desc);
        btn_update_notification = findViewById(R.id.btn_update_notification);

        notification_title.setText(getIntent().getStringExtra("title"));
        notification_desc.setText(getIntent().getStringExtra("description"));

        btn_update_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(notification_title.getText().toString().isEmpty()){
                    Toast.makeText(EditNotificationsActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(notification_desc.getText().toString().isEmpty()){
                    Toast.makeText(EditNotificationsActivity.this, "Enter Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    updateNotificationData();
                }
            }
        });
    }
    ProgressDialog p;

    private void updateNotificationData() {
        String title = notification_title.getText().toString();
        String description = notification_desc.getText().toString();
        String id = getIntent().getStringExtra("id");
        p = new ProgressDialog(EditNotificationsActivity.this);
        p.setMessage("Adding please wait");
        p.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.editnotifications(id,title,description);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                p.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(EditNotificationsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditNotificationsActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(EditNotificationsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                p.dismiss();
                Toast.makeText(EditNotificationsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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