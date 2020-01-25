package com.example.baniya;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baniya.model.OrderHistoryResponse;


import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>
{
    public OrderHistoryAdapter(List<OrderHistoryResponse> orderList)
    {
        this.orderList = orderList;
    }

    private List<OrderHistoryResponse> orderList;

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_row,parent,false);
        return new OrderHistoryAdapter.OrderHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position)
    {
//null check to be implemented late


      holder.orderIdTextView.setText("Order Id: "+orderList.get(position).getOrderId());
      holder.totalItemsTextView.setText("Items: "+orderList.get(position).getCart().getProductDTO().size());
      holder.totalPriceTextView.setText("Amount: ₹"+orderList.get(position).getCart().getTotal()+"\n \nDate: "+orderList.get(position).getTimestamp());
        Glide.with(holder.firstProductImage.getContext())
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(orderList.get(position).getCart().getProductDTO().get(0).getImageUrl()).into(holder.firstProductImage);

    }

    @Override
    public int getItemCount()
    {
        return orderList.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderIdTextView;
        TextView totalPriceTextView;
        TextView totalItemsTextView;
        ImageView firstProductImage;


        public OrderHistoryViewHolder(@NonNull View itemView)
        {
            super(itemView);
           orderIdTextView = itemView.findViewById(R.id.order_id);
           totalPriceTextView = itemView.findViewById(R.id.total_price);
           totalItemsTextView = itemView.findViewById(R.id.total_items);
           firstProductImage = itemView.findViewById(R.id.order_imageView);


        }
    }

}
