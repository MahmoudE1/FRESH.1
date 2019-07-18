package com.example.muslim.fresh_.Utils;

import com.example.muslim.fresh_.Retrofit.IFreshAPI;
import com.example.muslim.fresh_.Retrofit.RetrofitClient;
import com.example.muslim.fresh_.Retrofit.IFreshAPI;
import com.example.muslim.fresh_.Retrofit.RetrofitClient;

public
class Common {
    private static final String BASE_URL = "http://10.0.2.2/fresh/";
    public static
    IFreshAPI getAPI ()
    {

        return RetrofitClient.getClient(BASE_URL).create ( IFreshAPI.class );



    }
}
