package com.example.baniya;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewholder>
{
    private List<Product> productList;
    private IProductCommunicator iProductCommunicator;


    public ProductAdapter(List<Product> productList, IProductCommunicator iProductCommunicator)
    {
        this.productList = productList;
        this.iProductCommunicator = iProductCommunicator;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.row_layout,parent,false);
        return new MyViewholder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewholder holder, int position)
    {
        Product product = productList.get(position);


        holder.productRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.i("VANIK",productList.get(holder.getAdapterPosition()).getProductId());
                iProductCommunicator.openProductDetail(productList.get(holder.getAdapterPosition()).getProductId());
            }
        });

        if (product!= null )
        {
            String productDetails = product.getProductName();
            holder.productDetailsTextView.setText(productDetails);
            Glide.with(holder.imageView.getContext())
                    .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                    .load(product.getImageUrl()).into(holder.imageView);

        }


    }

    @Override
    public int getItemCount()
    {
        return productList.size();
    }



    public class MyViewholder extends RecyclerView.ViewHolder
    {
        TextView productDetailsTextView;
        ImageView imageView;
        LinearLayout productRow;

        public MyViewholder(@NonNull View itemView)
        {
            super(itemView);
            productDetailsTextView = itemView.findViewById(R.id.product_details);
            imageView = itemView.findViewById(R.id.image_view);
            productRow= itemView.findViewById(R.id.product_row);


        }
    }




}
