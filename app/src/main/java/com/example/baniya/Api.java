package com.example.baniya;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api
{

    String BASE_URL = "http://172.16.20.172:8080";


        @GET("/product/viewProductsByCategoryId/{id}")
        Call<List<Product>>getProducts(@Path("id") String id);

        @GET("/product/landingPage")
        Call<List<Product>>getLandingProducts();

        @GET("/product/viewProductById/{id}")
        Call<Product>getProduct(@Path("id") String  id);

        @GET("/merchant/{id}")
        Call<List<Merchant>>getMerchants(@Path("id")String id);

        @POST("/cart/addToCart")
        Call<Object> addToCart(@Body AddCartDTO addCartDTO);

        @GET("/cart/viewCart/{userId}")
        Call<List<ViewCartDTO>>viewCart(@Path("userId")String id);




}
