package com.example.mp3app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp3app.Activity.PlayNhacActivity;
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihat> baiHatArrayList;

    public BaiHatHotAdapter(Context context, ArrayList<Baihat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baiHat = baiHatArrayList.get(position);
        holder.txtCaSi.setText(baiHat.getCasi());
        holder.txtTen.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtCaSi;
        ImageView imgHinh, imgLuotThich;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.textviewbaihathot);
            txtCaSi = itemView.findViewById(R.id.textviewtencasibaihathot);
            imgHinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgLuotThich = itemView.findViewById(R.id.imageviewluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);
                    Dataservice dataService = APIService.getService();
                    Call<String> callBack = dataService.updateLuotThich("1", baiHatArrayList.get(getPosition()).getIdbaihat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketQua = response.body();
                            if (ketQua.equals("Success")) {
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bị Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotThich.setEnabled(false);
                }
            });
        }

    }
}