package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartResponseDTO implements Serializable
{
    @SerializedName("cartId")
    private String  cartId;
    private Boolean isSuccess;
    private String  errorMessage;

    public String getCartId()
    {
        return cartId;
    }

    public void setCartId(String cartId)
    {
        this.cartId = cartId;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



}
