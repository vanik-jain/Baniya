package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateCartDTO implements Serializable
{
    @SerializedName("productId")
    private String productId;
    private String merchantId;
    private int counter;

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }



}
