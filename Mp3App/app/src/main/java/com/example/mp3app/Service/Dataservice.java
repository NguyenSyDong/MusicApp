package com.example.mp3app.Service;

import com.example.mp3app.Model.Account;
import com.example.mp3app.Model.Album;
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.Model.ChuDe;
import com.example.mp3app.Model.Playlist;
import com.example.mp3app.Model.Quangcao;
import com.example.mp3app.Model.ResponseModel;
import com.example.mp3app.Model.TheLoai;
import com.example.mp3app.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<Baihat>> GetbaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> getDanhsachcacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdanhsachcacbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> Gettheloaitheochude(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> getSearchBaiHat(@Field("tukhoa") String tuKhoa);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<List<Account>> login(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("dangki.php")
    Call<String> Register(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

}
