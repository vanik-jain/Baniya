 package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class CartActivity extends AppCompatActivity implements ICartCommunicator
{

//    private  Intent gIntent;

    private RecyclerView rvCartProducts;
    private ViewCartDTO cartProduct;
    private CartAdapter cartAdapter;
    private SharedPreferences sharedPreferences;
    private  String authToken;
    private List<ProductDTOItem> cartList = new ArrayList<>();
    private TextView totalTextView;


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
         totalTextView = findViewById(R.id.total_text_view);
         sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
         rvCartProducts = findViewById(R.id.cart_recycler_view);
         cartAdapter = new CartAdapter(cartList,CartActivity.this);
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
         rvCartProducts.setLayoutManager(layoutManager);
         rvCartProducts.setItemAnimator(new DefaultItemAnimator());
         rvCartProducts.setAdapter(cartAdapter);
         Api api = App.getRetrofit().create(Api.class);
         authToken  =sharedPreferences.getString("auth_token",null);
         Call<ViewCartDTO>call = api.viewCart(authToken);
           call.enqueue(new Callback<ViewCartDTO>() {
               @Override
               public void onResponse(Call<ViewCartDTO> call, Response<ViewCartDTO> response) {
                   if (response.body() != null)
                   {
                       ViewCartDTO viewCartDTO = response.body();

                       cartList.clear();
                       cartList.addAll(viewCartDTO .getProductDTO());
                       if(cartList.size() == 0)
                       {
                           Toast.makeText(getApplicationContext(),"No Items in Cart",Toast.LENGTH_SHORT).show();
                       }
                       cartAdapter.notifyDataSetChanged();
                       totalTextView.setText("Total: ₹"+viewCartDTO.getTotal() );
                   }
               }

               @Override
               public void onFailure(Call<ViewCartDTO> call, Throwable t)
               {
                   Toast.makeText(getApplicationContext(),"Cart cannot be loaded",Toast.LENGTH_SHORT).show();
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

        Call<CartResponseDTO>call = api.updateCart(updateCartDTO,authToken);
        call.enqueue(new Callback<CartResponseDTO>() {
            @Override
            public void onResponse(Call<CartResponseDTO> call, Response<CartResponseDTO> response)
            {
                if (response.body()!=null)
                {
                    CartResponseDTO cartResponseDTO = response.body();
                    if (cartResponseDTO.getSuccess())
                    {
                        Toast.makeText(CartActivity.this, "Cart Updated", Toast.LENGTH_SHORT).show();
                        totalTextView.setText("Total: ₹"+cartResponseDTO.getTotal());
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponseDTO> call, Throwable t)
            {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void checkOut(View view)
    {
       Api api = App.getRetrofit().create(Api.class);

       Call<CartResponseDTO> call = api.checkout(authToken);
       call.enqueue(new Callback<CartResponseDTO>() {
           @Override
           public void onResponse(Call<CartResponseDTO> call, Response<CartResponseDTO> response)
           {

               if (response.body() != null)
               {
                   CartResponseDTO cartResponseDTO = response.body();
                   if (cartResponseDTO.getSuccess())
                   {
                       Toast.makeText(getApplicationContext(), "Order Placed!", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(CartActivity.this, LandingActivity.class));
                       finish();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(), cartResponseDTO.getErrorMessage(), Toast.LENGTH_SHORT).show();
                       finish();
                       startActivity(getIntent());
                   }


               }

           }

           @Override
           public void onFailure(Call<CartResponseDTO> call, Throwable t)
           {
               Toast.makeText(CartActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

           }
       });

    }



}
