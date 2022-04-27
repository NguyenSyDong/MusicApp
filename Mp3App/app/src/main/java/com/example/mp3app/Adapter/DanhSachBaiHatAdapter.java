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

import com.example.mp3app.Activity.DanhSachBaiHatActivity;
import com.example.mp3app.Activity.PlayNhacActivity;
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        holder.txtindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttenbaihat, txtcasi;
        ImageView imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.txttencasi);
            txtindex = itemView.findViewById(R.id.txtdanhsachindex);
            txttenbaihat = itemView.findViewById(R.id.txttenbaihat);
            imgluotthich = itemView.findViewById(R.id.imgviewluothichdanhsachbaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataService = APIService.getService();
                    Call<String> callBack = dataService.updateLuotThich("1", baihatArrayList.get(getPosition()).getIdbaihat());
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
                    imgluotthich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
