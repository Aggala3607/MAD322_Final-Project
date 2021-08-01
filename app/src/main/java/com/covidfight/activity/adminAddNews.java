package com.covidfight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.covidfight.R;

public class adminAddNews extends AppCompatActivity {

    EditText add_news_title, add_news_description;
    Button btn_add_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_news);

        add_news_title = findViewById(R.id.add_news_title);
        add_news_description = findViewById(R.id.add_news_desc);
        btn_add_news = findViewById(R.id.btn_add_news);
    }
}