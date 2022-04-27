package com.example.mp3app.Service;

public class APIService {
    private static String base_url = "https://appmusicprm.000webhostapp.com/Server/";

    public static Dataservice getService(){
        return APIRetrofitClient.getRetrofit(base_url).create(Dataservice.class);
    }
}
