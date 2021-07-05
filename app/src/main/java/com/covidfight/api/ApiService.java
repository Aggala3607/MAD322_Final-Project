package com.covidfight.api;


import com.covidfight.model.Account;
import com.covidfight.model.ResponseData;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService
{



    @GET("/covidfight/registration.php?")
    Call<ResponseData> user_registration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("user_key") String user_key,
            @Query("phone") String phone);

    @GET("/covidfight/login.php?")
    Call<ResponseData> user_login(
            @Query("email") String email,
            @Query("user_key") String user_key);


    @GET("/covidfight/adminlogin.php?")
    Call<ResponseData> adminlogin(
            @Query("email") String email,
            @Query("admin_key") String admin_key);

    @GET("covidfight/profile.php")
    Call<List<Account>> profile
            (@Query("email") String email);

    @GET("/covidfight/editprofile.php?")
    Call<ResponseData> editprofile(
            @Query("name") String name,
            @Query("email") String email,
            @Query("user_key") String user_key,
            @Query("phone") String phone);

    @GET("covidfight/resetpassword.php")
    Call<ResponseData> forgotPassword
            (@Query("email") String email);

    @GET("/covidfight/travel.php?")
    Call<ResponseData> travel(
            @Query("title") String title,
            @Query("description") String description);

    @GET("/covidfight/quarantine.php?")
    Call<ResponseData> quarantine(
            @Query("title") String title,
            @Query("description") String description);
}
