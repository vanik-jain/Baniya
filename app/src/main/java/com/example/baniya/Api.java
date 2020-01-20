package com.example.baniya;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api
{

    String BASE_URL = "http://172.16.20.172:8080";


        @GET("/product/viewProductsByCategoryId/{id}")
        Call<List<Product>>getProducts(@Path("id") String id);


        @GET("/product/viewProductById/{id}")
        Call<Product>getProduct(@Path("id") String  id);


        @GET("/cart/viewProductById/{id}")
        Call<Product>addToCart(@Path("id")String id);


}
