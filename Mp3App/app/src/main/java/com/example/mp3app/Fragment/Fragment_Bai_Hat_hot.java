package com.example.mp3app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp3app.Adapter.BaiHatHotAdapter;
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_hot extends Fragment {
    View view;
    RecyclerView recyclerViewBaiHatHot;
    BaiHatHotAdapter baiHatHotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_yeu_thich, container, false);
        recyclerViewBaiHatHot = view.findViewById(R.id.recycleviewbaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetbaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baiHatHotAdapter = new BaiHatHotAdapter(getActivity(), baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatHot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
