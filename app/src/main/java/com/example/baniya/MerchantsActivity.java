package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantsActivity extends AppCompatActivity implements IMerchantCommunicator{

    private RecyclerView rvMerchants;
    private List<Merchant>merchants;
    private MerchantAdapder merchantAdapder;
    private SharedPreferences sharedPreferences;
    private String authToken;
    private Intent gIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchants);
        rvMerchants = findViewById(R.id.merchant_recycler_view);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        gIntent = getIntent();
        String mProductId= gIntent.getStringExtra("productId");
        Api api = App.getRetrofit().create(Api.class);
        Call<List<Merchant>> call = api.getMerchants(mProductId);
        call.enqueue(new Callback<List<Merchant>>() {
            @Override
            public void onResponse(Call<List<Merchant>> call, Response<List<Merchant>> response)
            {
                if (response.body() != null)
                {
                    merchants = response.body();
                    merchantAdapder = new MerchantAdapder(merchants, MerchantsActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MerchantsActivity.this);
                    rvMerchants.setLayoutManager(layoutManager);
                    rvMerchants.setItemAnimator(new DefaultItemAnimator());
                    rvMerchants.setAdapter(merchantAdapder);
                }
            }

            @Override
            public void onFailure(Call<List<Merchant>> call, Throwable t)
            {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public void addMerchantProductToCart(String productId, String merchantId)
    {

        final Intent intent = new Intent(MerchantsActivity.this,CartActivity.class);
        Api api = App.getRetrofit().create(Api.class);
        AddCartDTO addCartDTO = new AddCartDTO();
        addCartDTO.setProductId(productId);
        addCartDTO.setMerchantId(merchantId);
        authToken = sharedPreferences.getString("auth_token",null);
        Call<CartResponseDTO> call = api.addToCart(addCartDTO,authToken);
        call.enqueue(new Callback<CartResponseDTO>() {
            @Override
            public void onResponse(Call<CartResponseDTO> call, Response<CartResponseDTO> response)
            {

                if (response.body() != null)
                {
                    Toast.makeText(getBaseContext(),"Item added to cart",Toast.LENGTH_SHORT).show();

                       // addOneItemToCartButton.setText("VIEW CART");
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<CartResponseDTO> call, Throwable t) {

            }
        });


    }
}
