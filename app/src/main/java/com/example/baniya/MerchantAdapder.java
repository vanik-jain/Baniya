package com.example.baniya;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MerchantAdapder extends RecyclerView.Adapter<MerchantAdapder.MerchantViewHolder>
{
    private List<Merchant> merchantList;
    private  IMerchantCommunicator iMerchantCommunicator;

    public MerchantAdapder(List<Merchant> merchantList, IMerchantCommunicator iMerchantCommunicator)
    {
        this.merchantList = merchantList;
        this.iMerchantCommunicator = iMerchantCommunicator;
    }

    @NonNull
    @Override
    public MerchantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.merchant_row_layout,parent,false);
        return new MerchantAdapder.MerchantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MerchantViewHolder holder, int position)
    {
        Merchant merchant = merchantList.get(position);
        if(merchant != null)
        {
            if(merchant.getMerchantName() != null)
            holder.merchantNameTextView.setText(merchant.getMerchantName());
            holder.merChantRatingTextView.setText(merchant.getRating().toString());
            holder.merchantPriceTextView.setText(String.valueOf(merchant.getPrice()));
            holder.merchantStock.setText(String.valueOf(merchant.getStock()));
            holder.addToCartButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                  Merchant merchant1 = merchantList.get(holder.getAdapterPosition());
                     iMerchantCommunicator.addMerchantProductToCart(merchant1.getProductId(),"vthfuyvuil",merchant1.getMerchantId());
                }
            });

        }


    }

    @Override
    public int getItemCount()
    {
        return merchantList.size();
    }

    public class MerchantViewHolder extends  RecyclerView.ViewHolder
    {
       TextView merchantNameTextView;
       TextView merChantRatingTextView;
       TextView merchantPriceTextView;
       TextView merchantStock;
       Button addToCartButton;

        public MerchantViewHolder(@NonNull View itemView)
        {
            super(itemView);
            merchantNameTextView = itemView.findViewById(R.id.merchant_name);
            merChantRatingTextView = itemView.findViewById(R.id.merchant_rating);
            merchantPriceTextView =itemView.findViewById(R.id.merchant_product_price);
            merchantStock = itemView.findViewById(R.id.merchant_product_stock);
            addToCartButton = itemView.findViewById(R.id.merchant_add_to_cart);


        }
    }

}
