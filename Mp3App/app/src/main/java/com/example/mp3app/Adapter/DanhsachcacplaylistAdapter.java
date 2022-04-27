package com.example.mp3app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mp3app.Activity.DanhSachBaiHatActivity;
import com.example.mp3app.Model.Playlist;
import com.example.mp3app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachcacplaylistAdapter extends RecyclerView.Adapter<DanhsachcacplaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> mangPlaylist;


    public DanhsachcacplaylistAdapter(Context context, ArrayList<Playlist> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }


    @Override
    public DanhsachcacplaylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_cac_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DanhsachcacplaylistAdapter.ViewHolder holder, int position) {
        Playlist playList = mangPlaylist.get(position);
        Picasso.with(context).load(playList.getHinhPlaylist()).into(holder.imgHinhNen);
        holder.txtTenPlaylist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgHinhNen;
        TextView txtTenPlaylist;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txtTenPlaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist", mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
