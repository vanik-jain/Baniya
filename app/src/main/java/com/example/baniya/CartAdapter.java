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
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{
    private List<ViewCartDTO> cartProducts;
    private ICartCommunicator iCartCommunicator;

    public CartAdapter(List<ViewCartDTO> cartProducts, ICartCommunicator iCartCommunicator)
    {
        this.cartProducts = cartProducts;
        this.iCartCommunicator = iCartCommunicator;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_row,parent,false);
        return new CartAdapter.CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position)
    {
        ViewCartDTO viewCartDTO = cartProducts.get(position);
        if (viewCartDTO != null)
        {
            holder.cartProductDetailsTextView.setText(viewCartDTO.toString());
            Glide.with(holder.cartProductImageView.getContext())
                    .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .load(viewCartDTO.getImageUrl()).into(holder.cartProductImageView);
            holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener()
            {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue)
                {
                  // iCartCommunicator.
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public  class CartViewHolder extends RecyclerView.ViewHolder
    {
      TextView cartProductDetailsTextView ;
      ImageView cartProductImageView;
      ElegantNumberButton elegantNumberButton;

        public CartViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cartProductDetailsTextView = itemView.findViewById(R.id.cart_product_name);
            cartProductImageView = itemView.findViewById(R.id.cart_product_image);
            elegantNumberButton = itemView.findViewById(R.id.elegant_button);

        }
    }
}
