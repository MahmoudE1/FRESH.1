package com.example.muslim.fresh_.Retrofit;

import com.example.muslim.fresh_.model.CheckUserResponse;
import com.example.muslim.fresh_.model.User;
import com.example.muslim.fresh_.model.CheckUserResponse;
import com.example.muslim.fresh_.model.User;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public
interface IFreshAPI  {
    @FormUrlEncoded
    @POST("checkUser.php")
    Call<CheckUserResponse> checkUserExists(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("register.php")
    Call<User> registerNewUser(@Field("phone") String phone,
                               @Field("name") String name,
                               @Field("address") String address,
                               @Field("birthdate") String birthdate);
    @FormUrlEncoded
    @POST("getregister.php")
    Call<User> getUserInformation(@Field("phone") String phone);


}
