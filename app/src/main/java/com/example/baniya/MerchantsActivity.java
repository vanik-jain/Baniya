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

public class MerchantsActivity extends AppCompatActivity implements IMerchantCommunicator{

    private RecyclerView rvMerchants;
    private List<Merchant>merchants;
    private MerchantAdapder merchantAdapder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchants);
        rvMerchants = findViewById(R.id.merchant_recycler_view);


        Api api = App.getRetrofit().create(Api.class);
        Call<List<Merchant>> call = api.getMerchants("1");

        call.enqueue(new Callback<List<Merchant>>() {
            @Override
            public void onResponse(Call<List<Merchant>> call, Response<List<Merchant>> response)
            {
                merchants = response.body();
                merchantAdapder = new MerchantAdapder(merchants,MerchantsActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MerchantsActivity.this);
                rvMerchants.setLayoutManager(layoutManager);
                rvMerchants.setItemAnimator(new DefaultItemAnimator());
                rvMerchants.setAdapter(merchantAdapder);
            }

            @Override
            public void onFailure(Call<List<Merchant>> call, Throwable t)
            {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public void addMerchantProductToCart(String userId, String productId, String merchantId)
    {

        Intent intent = new Intent(MerchantsActivity.this,CartActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("productId",productId);
        intent.putExtra("merchantId",merchantId);
        startActivity(intent);

    }
}
