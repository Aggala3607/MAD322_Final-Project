package com.covidfight.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.covidfight.R;

import java.util.List;

public class AdminInfoAdapter extends BaseAdapter {
    List<Info> info;
    Context con;


    public AdminInfoAdapter(List<Info> info, Context con) {
        this.info = info;
        this.con = con;
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
        View q = obj1.inflate(R.layout.child_travel, null);

        TextView tvquarantinetitle = (TextView) q.findViewById(R.id.tvquarantinetitle);
        tvquarantinetitle.setText(info.get(pos).getTitle());

        TextView tvqua = (TextView) q.findViewById(R.id.tvquarantine);
        tvqua.setText(info.get(pos).getDescription());


        return q;
    }

}