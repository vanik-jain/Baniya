package com.example.baniya;

import androidx.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<String> addToCart(@Body AddCartDTO addCartDTO, @Header("Auth") String authToken);

    @GET("/cart/viewCart/")
    Call<List<ViewCartDTO>>viewCart(@Header("Auth")String authToken);

    @POST("/auth/signup")
    Call<SignUpResponseDTO>sendSignupCredentials(@Body SignUpRequestDTO signUpRequestDTO);

    @POST("/auth/login")
    Call<LoginResponseDTO>sendLoginCredentials(@Body LoginRequestDTO loginRequestDTO);

    @POST("/cart/updateCart")
    Call<String>updateCart(@Body UpdateCartDTO updateCartDTO,@Header("Auth") String authToken);

    @GET("/order/checkout")
    Call<Object>checkout(@Header("Auth") String authToken);


}
