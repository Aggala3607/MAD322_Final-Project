package com.covidfight.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidfight.R;

import java.util.Arrays;
import java.util.List;

public class SelfAssessmentActivity extends AppCompatActivity {

    TextView tvquestion,tvresult;
    Button btnyes,btnno;
    int count=0;
    int qcount=0;
    List<String> question = Arrays.asList("fever ?","Dry cough ?","Tiredness ?","Difficulty breathing or shortness of breath ?","Chest pain or pressure ?","Loss of speech or movement ?");
    List<List<String>> answers =Arrays.asList(Arrays.asList("yes","no"),Arrays.asList("yes","no"),Arrays.asList("yes","no"),Arrays.asList("yes","no"),Arrays.asList("yes","no"),Arrays.asList("yes","no"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_assessment);
        getSupportActionBar().setTitle("Self Assessment");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvquestion=(TextView)findViewById(R.id.tvquestion);
        btnyes=(Button)findViewById(R.id.btnyes);
        tvquestion.setText(question.get(qcount));
        tvresult=(TextView)findViewById(R.id.tvresult);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                qcount++;
               // tvquestion.setText(question.get(qcount));

                if(qcount>=question.size())
                {
                    tvresult.setVisibility(View.VISIBLE);
                    tvquestion.setVisibility(View.GONE);
                    btnyes.setVisibility(View.GONE);
                    btnno.setVisibility(View.GONE);
                    if(count>3)
                    {
//                        tvresult.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Covid sympton",Toast.LENGTH_LONG).show();
                        tvresult.setText("Covid sympton. please contact near by covid centers");
                    }
                    else {
//                        tvresult.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"No Covid sympton",Toast.LENGTH_LONG).show();
                        tvresult.setText("No Covid sympton.");
                    }
                }
                else
                {
                    tvquestion.setText(question.get(qcount));
                }

            }
        });

        btnno=(Button)findViewById(R.id.btnno);
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qcount++;
               // tvquestion.setText(question.get(qcount));

                if(qcount>=question.size())
                {
                    tvresult.setVisibility(View.VISIBLE);
                    tvquestion.setVisibility(View.GONE);
                    btnyes.setVisibility(View.GONE);
                    btnno.setVisibility(View.GONE);
                    if(count>3)
                    {
//                        tvresult.setVisibility(View.VISIBLE);
                        tvresult.setText("Covid sympton. please contact near by covid centers");
                        Toast.makeText(getApplicationContext(),"Covid sympton",Toast.LENGTH_LONG).show();
                    }

                    else {
//                        tvresult.setVisibility(View.VISIBLE);
                        tvresult.setText("No Covid sympton.");
                        Toast.makeText(getApplicationContext(),"No Covid sympton",Toast.LENGTH_LONG).show();

                    }
                }

                else
                {
                    tvquestion.setText(question.get(qcount));
                }
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