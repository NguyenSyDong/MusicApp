package com.example.mp3app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mp3app.Activity.DanhSachBaiHatActivity;
import com.example.mp3app.Model.Album;
import com.example.mp3app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txttencasialbum.setText(album.getTencasiAlbum());
        holder.txttenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txttenalbum, txttencasialbum;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
            txttencasialbum = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
