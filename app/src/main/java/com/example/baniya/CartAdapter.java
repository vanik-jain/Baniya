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
    private List<ProductDTOItem> cartProducts;
    private ICartCommunicator iCartCommunicator;

    public CartAdapter(List<ProductDTOItem> cartProducts, ICartCommunicator iCartCommunicator)
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
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position)
    {
        final ProductDTOItem productDTOItem = cartProducts.get(position);
        if (productDTOItem != null)
        {
            holder.cartProductNameTextView.setText(productDTOItem.getProductName());
            holder.cartProductPriceTextview.setText("₹"+productDTOItem.getPrice());
            Glide.with(holder.cartProductImageView.getContext())
                    .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .load(productDTOItem.getImageUrl()).into(holder.cartProductImageView);
            holder.elegantNumberButton.setNumber(String.valueOf(productDTOItem.getCounter()));
            holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener()
            {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue)
                {
                    ProductDTOItem productDTOItem1 = cartProducts.get(holder.getAdapterPosition());
                    iCartCommunicator.updateCartOnClick(productDTOItem1.getProductId(),productDTOItem1.getMerchantId(),newValue);
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
      TextView cartProductNameTextView;
      ImageView cartProductImageView;
      ElegantNumberButton elegantNumberButton;
      TextView cartProductPriceTextview;


        public CartViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cartProductNameTextView = itemView.findViewById(R.id.cart_product_name);
            cartProductImageView = itemView.findViewById(R.id.cart_product_image);
            cartProductPriceTextview = itemView.findViewById(R.id.cart_product_price);
            elegantNumberButton = itemView.findViewById(R.id.elegant_button);

        }
    }
}
