package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.baniya.model.OrderHistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity
{

    private RecyclerView rvOrderHistory;
    private SharedPreferences sharedPreferences;
    private OrderHistoryAdapter orderHistoryAdapter;
    private List<OrderHistoryResponse> orderHistoryResponses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        rvOrderHistory = findViewById(R.id.order_history_recycler_view);
        orderHistoryAdapter = new OrderHistoryAdapter(orderHistoryResponses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OrderHistoryActivity.this);
        rvOrderHistory.setLayoutManager(layoutManager);
        rvOrderHistory.setItemAnimator(new DefaultItemAnimator());
        rvOrderHistory.setAdapter(orderHistoryAdapter);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        String authToken = sharedPreferences.getString("auth_token",null);
        Api api = App.getRetrofit().create(Api.class);//null check to be added later
        Call<List<OrderHistoryResponse>> call = api.getOrderHistory(authToken);
        call.enqueue(new Callback<List<OrderHistoryResponse>>()
        {
            @Override
            public void onResponse(Call<List<OrderHistoryResponse>> call, Response<List<OrderHistoryResponse>> response)
            {
                if (response.body() != null)
                {
                    orderHistoryResponses.clear();
                    orderHistoryResponses.addAll(response.body());
                    orderHistoryAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<OrderHistoryResponse>> call, Throwable t)
            {

            }
        });




    }
}
