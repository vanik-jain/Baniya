 package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class CartActivity extends AppCompatActivity implements ICartCommunicator
{

//    private  Intent gIntent;

    private RecyclerView rvCartProducts;
    private List<ViewCartDTO> cartProducts;
    private CartAdapter cartAdapter;
    private SharedPreferences sharedPreferences;
    private  String authToken;


    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
//        gIntent = getIntent();
//        String productId = gIntent.getStringExtra("productId");
//
//        String merchantId = gIntent.getStringExtra("merchantId");
//
//        String userId = gIntent.getStringExtra("userId");
         sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
         rvCartProducts = findViewById(R.id.cart_recycler_view);
         Api api = App.getRetrofit().create(Api.class);
         authToken  =sharedPreferences.getString("auth_token",null);
         Call<List<ViewCartDTO>> call = api.viewCart(authToken);
           call.enqueue(new Callback<List<ViewCartDTO>>() {
            @Override
            public void onResponse(Call<List<ViewCartDTO>> call, Response<List<ViewCartDTO>> response)
            {
                if (response.body() != null)
                {
                    cartProducts = response.body();
                    cartAdapter = new CartAdapter(cartProducts,CartActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
                    rvCartProducts.setLayoutManager(layoutManager);
                    rvCartProducts.setItemAnimator(new DefaultItemAnimator());
                    rvCartProducts.setAdapter(cartAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<ViewCartDTO>> call, Throwable t)
            {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




//       AddCartDTO addCartDTO = new AddCartDTO();
//              addCartDTO.setMerchantId(merchantId);
//              addCartDTO.setProductId(productId);
//              addCartDTO.setUserId(userId);
//
//              Call<Object> call =api.addToCart(addCartDTO);
//              call.enqueue(new Callback<Object>()
//              {
//                  @Override
//                  public void onResponse(Call<Object> call, Response<Object> response) {
//
//                  }
//
//                  @Override
//                  public void onFailure(Call<Object> call, Throwable t) {
//
//                  }
//              });







    }

    @Override
    public void updateCartOnClick(String productId, String merchantId, int counter)
    {
        Api api = App.getRetrofit().create(Api.class);
        UpdateCartDTO updateCartDTO = new UpdateCartDTO();
        updateCartDTO.setMerchantId(merchantId);
        updateCartDTO.setCounter(counter);
        updateCartDTO.setProductId(productId);

        Call<String> call = api.updateCart(updateCartDTO,authToken);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (response.body()!=null)
                {
                    Toast.makeText(CartActivity.this, "Cart Updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void checkOut(View view)
    {
       Api api = App.getRetrofit().create(Api.class);

       Call<Object> call = api.checkout(authToken);
       call.enqueue(new Callback<Object>()
       {
           @Override
           public void onResponse(Call<Object> call, Response<Object> response)
           {
             if (response.body() != null)
             {
                 Toast.makeText(CartActivity.this,"Order Placed!",Toast.LENGTH_SHORT).show();
             }


           }

           @Override
           public void onFailure(Call<Object> call, Throwable t)
           {
               Toast.makeText(CartActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
           }
       });

    }



}
