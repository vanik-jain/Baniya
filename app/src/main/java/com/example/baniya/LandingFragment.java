package com.example.baniya;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingFragment extends Fragment implements IProductCommunicator {

    private  RecyclerView rvProducts;
    private  ProductAdapter productAdapter;
    private  List<Product> productList;
    private String categoryId;
 //   private LinearLayout progressBarLinearLayout;

    public LandingFragment(String categoryId)
    {
        this.categoryId = categoryId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_land_page,container,false);
        rvProducts = view.findViewById(R.id.recycler_view);
      //  progressBarLinearLayout = view.findViewById(R.id.progressbar_layout);
        Api api = App.getRetrofit().create(Api.class);

        Call<List<Product>> call = null;

        if(categoryId.equals("landing"))

        {
            call = api.getLandingProducts();
        }

        else  if(categoryId.equals("1"))
        {
            call = api.getProducts("1");
        }

        else if (categoryId.equals("2"))
        {
             call = api.getProducts("2");
        }

        else if (categoryId.equals("3"))
        {
            call = api.getProducts("3");
        }
        else if (categoryId.equals("4"))
        {
            call = api.getProducts("4");
        }
        else if (categoryId.equals("5"))
        {
            call = api.getProducts("5");
        }





        call.enqueue(new Callback<List<Product>>()
        {
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response)
            {
                if(response.body() != null)
                {
                   // progressBarLinearLayout.setVisibility(View.GONE);
                    productList = response.body();
                    productAdapter = new ProductAdapter(productList, LandingFragment.this);
                    RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(view.getContext());
                    rvProducts.setLayoutManager(layoutManager);
                    rvProducts.setItemAnimator(new DefaultItemAnimator());
                    rvProducts.setAdapter(productAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t)
            {
//                progressBarLinearLayout.setVisibility(View.GONE);
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
            });

        return view;
    }

    @Override
    public void openProductDetail(String productId)
    {
        Log.i("VANIK1",productId);
        Intent intent = new Intent(getContext(),ProductDescriptionActivity.class);
        intent.putExtra("productID",productId);
        startActivity(intent);

    }
}
