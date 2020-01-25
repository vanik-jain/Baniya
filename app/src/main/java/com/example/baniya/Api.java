package com.example.baniya;

import androidx.annotation.Nullable;

import com.example.baniya.model.OrderHistoryResponse;

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
    Call<CartResponseDTO> addToCart(@Body AddCartDTO addCartDTO, @Header("Auth") String authToken);

    @GET("/cart/viewCart/")
    Call<ViewCartDTO>viewCart(@Header("Auth")String authToken);

    @POST("/auth/signup")
    Call<SignUpResponseDTO>sendSignupCredentials(@Body SignUpRequestDTO signUpRequestDTO);

    @POST("/auth/login")
    Call<LoginResponseDTO>sendLoginCredentials(@Body LoginRequestDTO loginRequestDTO,@Nullable @Header("token") String token,@Header("provider")String provider,@Header("web")String web);

    @POST("/cart/updateCart")
    Call<CartResponseDTO>updateCart(@Body UpdateCartDTO updateCartDTO, @Header("Auth") String authToken);

    @GET("/order/checkout")
    Call<CartResponseDTO>checkout(@Header("Auth") String authToken);

    @GET("/authenticate/user/me")
    Call<UserDetailsDTO>getUserDetails(@Header("Auth") String authToken);

    @GET("/order/orderHistory")
    Call<List<OrderHistoryResponse>>getOrderHistory(@Header("Auth") String authToken);


}
