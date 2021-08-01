package com.covidfight.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.covidfight.EditNewsActivity;
import com.covidfight.R;
import com.covidfight.activity.AdminNotificationsActivity;
import com.covidfight.activity.EditNotificationsActivity;
import com.covidfight.api.ApiService;
import com.covidfight.api.RetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAdapter extends BaseAdapter {
    List<Info> info;
    Context con;
    String url= "http://covidinformation.live/covidfight/";

    public NewsAdapter(List<Info> info, Context con) {
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
        View q = obj1.inflate(R.layout.child_news, null);

        TextView tvnotificationtitle = (TextView) q.findViewById(R.id.tvnotificationtitle);
        tvnotificationtitle.setText(info.get(pos).getTitle());

        TextView tvnotydescription = (TextView) q.findViewById(R.id.tvnotydescription);
        tvnotydescription.setText(info.get(pos).getDescription());

        ImageView image=(ImageView)q.findViewById(R.id.image);
        Glide.with(con).load(url+info.get(pos).getImage()).into(image);

        Button nedit = (Button) q.findViewById(R.id.nedit);
        nedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(con, EditNewsActivity.class);
//                intent.putExtra("id",info.get(pos).getNid());
//                intent.putExtra("title",info.get(pos).getTitle());
//                intent.putExtra("description",info.get(pos).getDescription());
//                con.startActivity(intent);

                //Pending edit in news


            }
        });

        Button ndelete = (Button) q.findViewById(R.id.ndelete);
        ndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
                Call<ResponseData> call = service.deletenews(info.get(pos).getNid());
                call.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                        if(response.body()==null){
                            Toast.makeText(con,"Server issue",Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent=new Intent(con, AdminNotificationsActivity.class);
                            con.startActivity(intent);
                            Toast.makeText(con," Deleted successfully",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {

                        Toast.makeText(con, "Please try later", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return q;
    }

}