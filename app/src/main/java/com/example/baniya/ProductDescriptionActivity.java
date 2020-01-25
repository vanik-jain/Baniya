package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;


import android.content.Intent;
import android.content.SharedPreferences;
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
    private TextView productRatingTextView;
    private SharedPreferences sharedPreferences;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
   // private LinearLayout progressBarLinearLayout;
 //   private Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        productImage = findViewById(R.id.product_image);
        productUsp = findViewById(R.id.product_usp);
        productName = findViewById(R.id.product_name_heavy);
        productPrice = findViewById(R.id.product_price);
        productRatingTextView = findViewById(R.id.product_rating);
        addOneItemToCartButton = findViewById(R.id.add_one_item_to_cart_button);
//        progressBarLinearLayout = findViewById(R.id.progressbar_layout);
//        progressBarLinearLayout.setVisibility(View.VISIBLE);
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
//                    progressBarLinearLayout.setVisibility(View.GONE);
                    Log.i("VANIK",product.toString());
                    productName.setText(product.getProductName());
                    productUsp.setText(product.getProductDescription());
                    productPrice.setText("₹"+String.valueOf((product.getPrice())));
                    productRatingTextView.setText(product.getProductRating()+"\u2605");
                    Glide.with(productImage.getContext())
                            .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                            .load(product.getImageUrl()).into(productImage);
                }

                else
                {
//                    progressBarLinearLayout.setVisibility(View.GONE);
                    Toast.makeText(ProductDescriptionActivity.this,"Product not Available",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {
                Toast.makeText(ProductDescriptionActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


//        callbackManager= CallbackManager.Factory.create();
//        loginButton =  findViewById(R.id.login_button);
//        loginButton.setReadPermissions(Arrays.asList(EMAIL));




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

                  {
                      addOneItemToCart();

                  }
                  else
                  {
                      Toast.makeText(getBaseContext(),"Item out of stock",Toast.LENGTH_SHORT).show();
                  }



              }


    }

    public void displayMerchantList(View view)
    {
        Intent intent = new Intent(ProductDescriptionActivity.this,MerchantsActivity.class);
        intent.putExtra("productId",product.getProductId());
        startActivity(intent);
    }


    public void addOneItemToCart() {

        Api api = App.getRetrofit().create(Api.class);
        AddCartDTO addCartDTO = new AddCartDTO();
        addCartDTO.setMerchantId(product.getMerchantId());
        addCartDTO.setProductId(product.getProductId());
        //  addCartDTO.setUserId(userId);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("auth_token", null);
        Call<CartResponseDTO> call = api.addToCart(addCartDTO, authToken);

        call.enqueue(new Callback<CartResponseDTO>() {
            @Override
            public void onResponse(Call<CartResponseDTO> call, Response<CartResponseDTO> response)
            {
                if (response.body() != null) {
                    CartResponseDTO cartResponseDTO = response.body();
                    if (cartResponseDTO.getSuccess()) {
                        Toast.makeText(getBaseContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
                        addOneItemToCartButton.setText("VIEW CART");
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponseDTO> call, Throwable t)
            {

                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

             }
        });


    }


}
