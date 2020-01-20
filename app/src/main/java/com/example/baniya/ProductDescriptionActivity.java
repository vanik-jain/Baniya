package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDescriptionActivity extends AppCompatActivity
{
    private ImageView productImage;
    private TextView productUsp;
    private Api api;
    private Call<Product> call;
    private Intent gIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        productImage = findViewById(R.id.product_image);
        productUsp = findViewById(R.id.product_usp);
        api = App.getRetrofit().create(Api.class);
        gIntent = getIntent();
        String productId = gIntent.getStringExtra("productID");
       // Log.i("VANIK2",gIntent.getStringExtra("productId"));
         call= api.getProduct(productId);

        call.enqueue(new Callback<Product>()
        {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response)
            {
                Product product = response.body();
                if (product != null)
                {
                    productUsp.setText(product.getProductDescription());
                    Glide.with(productImage.getContext())
                            .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                            .load(product.getImageUrl()).into(productImage);
                }

                else
                {
                    Toast.makeText(ProductDescriptionActivity.this,"Product not Available",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {
                Toast.makeText(ProductDescriptionActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
