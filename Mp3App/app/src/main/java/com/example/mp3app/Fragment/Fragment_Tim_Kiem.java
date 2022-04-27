package com.example.mp3app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mp3app.Adapter.SearchBaiHatAdapter;
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {

    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewSearchBaiHat;
    TextView textViewKhongCoDuLieu;
    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewSearchBaiHat = view.findViewById(R.id.recycleviewsearchbaihat);
        textViewKhongCoDuLieu = view.findViewById(R.id.textviewkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchBaiHat(String query){
        Dataservice dataService = APIService.getService();
        Call<List<Baihat>> callBack = dataService.getSearchBaiHat(query);
        callBack.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangBaiHat = (ArrayList<Baihat>) response.body();
                if(mangBaiHat.size() > 0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangBaiHat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearchBaiHat.setLayoutManager(linearLayoutManager);
                    recyclerViewSearchBaiHat.setAdapter(searchBaiHatAdapter);
                    textViewKhongCoDuLieu.setVisibility(View.GONE);
                    recyclerViewSearchBaiHat.setVisibility(View.VISIBLE);
                }else{
                    recyclerViewSearchBaiHat.setVisibility(View.GONE);
                    textViewKhongCoDuLieu.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
