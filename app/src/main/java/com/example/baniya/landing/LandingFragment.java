package com.example.baniya.landing;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baniya.R;
import com.example.baniya.retrofitsetup.Api;
import com.example.baniya.retrofitsetup.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingFragment extends Fragment
{

    public LandingFragment(String string)
    {
        Log.i("DTO",string);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
//
//        Api api = App.getRetrofit().create(Api.class);
//        Call<List<Product>> call = api.getProduct();
//        call.enqueue(new Callback<List<Product>>()
//        {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response)
//            {
//                if(response.body() != null) {
//                    List<Product> employeeList = response.body();
//                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
//                    ProductAdapter productAdapter = new ProductAdapter(employeeList);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                    recyclerView.setLayoutManager(layoutManager);
//                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    recyclerView.setAdapter(productAdapter);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t)
//            {
//                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });




        return inflater.inflate(R.layout.fragment_land_page,container,false);
    }
}
