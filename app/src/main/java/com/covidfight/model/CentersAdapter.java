package com.covidfight.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.covidfight.R;

import java.util.List;

public class CentersAdapter extends BaseAdapter {
    List<Info> info;
    Context con;


    public CentersAdapter(List<Info> info, Context con) {
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
        View q = obj1.inflate(R.layout.child_center, null);

        TextView tvname = (TextView) q.findViewById(R.id.tvname);
        tvname.setText(info.get(pos).getName());

        TextView tvcity = (TextView) q.findViewById(R.id.tvcity);
        tvcity.setText(info.get(pos).getCity());

        TextView tvphone = (TextView) q.findViewById(R.id.tvphone);
        tvphone.setText(info.get(pos).getPhone());

        TextView tvaddress = (TextView) q.findViewById(R.id.tvaddress);
        tvaddress.setText(info.get(pos).getAddress());
        return q;
    }

}