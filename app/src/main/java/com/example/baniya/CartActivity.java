 package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
         rvCartProducts = findViewById(R.id.cart_recycler_view);
        Api api = App.getRetrofit().create(Api.class);
        Call<List<ViewCartDTO>> call = api.viewCart("1");
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
    public void updateCart() {

    }
}
