package com.covidfight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int ScreenDisplay = 2000;
        Thread t1=new Thread(){
            int wait1=1;
            public void run(){
                try{
                    while(wait1<=ScreenDisplay )
                    {
                        sleep(100);
                        wait1+=100;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                   // Intent i= new Intent(MainActivity.this, UserLoginActivity.class);
                   // startActivity(i);
                    finish();

                }
            }
        };
        t1.start();
    }
}