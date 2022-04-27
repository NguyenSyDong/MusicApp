package com.example.mp3app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mp3app.Model.Baihat;
import com.example.mp3app.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> mangBaiHat;

    public PlayNhacAdapter(Context context, ArrayList<Baihat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baiHat = mangBaiHat.get(position);
        holder.txtCaSi.setText(baiHat.getCasi());
        holder.txtIndex.setText(position + 1 + "");
        holder.txtTenBaiHat.setText(baiHat.getTenbaihat());
    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex,txtTenBaiHat,txtCaSi;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCaSi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtIndex = itemView.findViewById(R.id.textviewplaynhacindex);
            txtTenBaiHat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
        }
    }
}
