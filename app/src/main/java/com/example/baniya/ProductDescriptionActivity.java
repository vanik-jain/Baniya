package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private TextView productName;
    private TextView productPrice;
    private Api api;
    private Call<Product> call;
    private Intent gIntent;
    private Product product;
    private Button addOneItemToCartButton;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        productImage = findViewById(R.id.product_image);
        productUsp = findViewById(R.id.product_usp);
        productName = findViewById(R.id.product_name_heavy);
        productPrice = findViewById(R.id.product_price);
        addOneItemToCartButton = findViewById(R.id.add_one_item_to_cart_button);
        addOneItemToCartButton.setText("ADD TO CART");
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
                product = response.body();

                if (product != null)
                {
                    Log.i("VANIK",product.toString());
                    productName.setText(product.getProductName());
                    productUsp.setText(product.getProductDescription());
                    productPrice.setText("₹"+String.valueOf((product.getPrice())));
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

    public void addToCart(View view)
    {
            Intent intent = new Intent(ProductDescriptionActivity.this,CartActivity.class);
//            intent.putExtra("userId","mcwdomoe");
//            intent.putExtra("productId",product.getProductId());
//            intent.putExtra("merchantId",product.getMerchantId());
             if (addOneItemToCartButton.getText().toString().equals("VIEW CART"))
             {
                 startActivity(intent);
                 finish();
             }
             else
              {
                  if (product.getStock() >= 1 )

                  {addOneItemToCart();
                  addOneItemToCartButton.setText("VIEW CART");}
                  else
                  {
                      Toast.makeText(getBaseContext(),"Item out of stock",Toast.LENGTH_SHORT).show();
                  }



              }


    }

    public void displayMerchantList(View view)
    {
        startActivity(new Intent(ProductDescriptionActivity.this,MerchantsActivity.class));
    }


    public void addOneItemToCart()
    {
        final String userId = "5" ;
        Api api = App.getRetrofit().create(Api.class);
        AddCartDTO addCartDTO = new AddCartDTO();
        addCartDTO.setMerchantId(product.getMerchantId());
        addCartDTO.setProductId(product.getProductId());
        addCartDTO.setUserId(userId);

        Call<String> call =api.addToCart(addCartDTO);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {

                if(response.body() != null && response.body().equals(userId))
                {
                    Toast.makeText(getBaseContext(),"Item added to cart",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Item cannot be added",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }


}
