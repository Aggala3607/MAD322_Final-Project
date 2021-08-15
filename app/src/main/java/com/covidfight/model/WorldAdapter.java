package com.covidfight.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.covidfight.R;
import com.covidfight.activity.AdminDashboardActivity;
import com.covidfight.activity.EditNotificationsActivity;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldAdapter extends BaseAdapter {
    List<WorldData> info,search;
    Context con;


    public WorldAdapter(List<WorldData> info, Context con) {
        this.info = info;
        this.con = con;
        this.search=info;
        this.info = new ArrayList<WorldData>();
        this.info.addAll(info);
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View q = obj1.inflate(R.layout.child_world, null);

        TextView tvcountry = (TextView) q.findViewById(R.id.tvcountry);
        tvcountry.setText("Country Name : "+info.get(pos).getCountry());

        TextView tvinfected = (TextView) q.findViewById(R.id.tvinfected);
        tvinfected.setText("Infected : "+info.get(pos).getInfected());

        TextView tvtested = (TextView) q.findViewById(R.id.tvtested);
        tvtested.setText("Tested : "+info.get(pos).getTested());

        TextView tvrecovered = (TextView) q.findViewById(R.id.tvrecovered);
        tvrecovered.setText("Recovered : "+info.get(pos).getRecovered());

        TextView tvdeceased = (TextView) q.findViewById(R.id.tvdeceased);
        tvdeceased.setText("Deceased : "+info.get(pos).getDeceased());


        TextView tvwebsitelink = (TextView) q.findViewById(R.id.tvwebsitelink);
        tvwebsitelink.setText("WebLink : "+info.get(pos).getSourceUrl());

        tvwebsitelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url =info.get(pos).getSourceUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                con.startActivity(i);
            }
        });


        return q;
    }

    public void searchCountryWiseData(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        info.clear();
        if (charText.length() == 0) {
            info.addAll(search);
        } else {
            for (WorldData w : search) {
                if (w.getCountry().toLowerCase(Locale.getDefault()).contains(charText)) {
                    info.add(w);
                }
            }
        }
        notifyDataSetChanged();
    }

}