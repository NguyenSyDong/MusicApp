package com.example.mp3app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mp3app.Activity.DanhsachtheloaitheochudeActivity;
import com.example.mp3app.Model.ChuDe;
import com.example.mp3app.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class DanhsachtatcachudeAdapter extends RecyclerView.Adapter<DanhsachtatcachudeAdapter.ViewHolder>{

    Context context;
    ArrayList<ChuDe> mangChuDe;

    public DanhsachtatcachudeAdapter(Context context, ArrayList<ChuDe> mangChuDe) {
        this.context = context;
        this.mangChuDe = mangChuDe;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_cac_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChuDe chuDe = mangChuDe.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgChuDe);
    }

    @Override
    public int getItemCount() {
        return mangChuDe.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgChuDe;

        public ViewHolder(View itemView) {
            super(itemView);
            imgChuDe = itemView.findViewById(R.id.imageviewdongcacchude);
            imgChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude",mangChuDe.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
