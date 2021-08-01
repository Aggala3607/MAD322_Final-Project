package com.covidfight.api;



import com.covidfight.model.Account;
import com.covidfight.model.Info;
import com.covidfight.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

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


    @GET("/covidfight/centers.php?")
    Call<ResponseData> centers(
            @Query("name") String name,
            @Query("city") String city,
            @Query("phone") String phone,
            @Query("address") String address);


    @GET("/covidfight/notifications.php?")
    Call<ResponseData> notifications(
            @Query("title") String title,
            @Query("description") String description);

    @GET("/covidfight/quarantine.php?")
    Call<ResponseData> quarantine(
            @Query("title") String title,
            @Query("description") String description);


    @GET("/covidfight/getquarantineinfo.php?")
    Call<List<Info>> getquarantineinfo();

    @GET("/covidfight/gettravelinfo.php?")
    Call<List<Info>> gettravelinfo();

    @GET("/covidfight/getnotifications.php?")
    Call<List<Info>> getnotifications();


    @GET("/covidfight/getcenters.php?")
    Call<List<Info>> getcenters();


}
