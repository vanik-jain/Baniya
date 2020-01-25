package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddCartDTO implements Serializable
{
	@SerializedName("productId")
	private String productId;
	private String merchantId;
	//private String userId;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}
//
//	public void setUserId(String userId){
//		this.userId = userId;
//	}
//
//	public String getUserId(){
//		return userId;
//	}


	@Override
	public String toString()
	{
		return "AddCartDTO{" +
				"productId='" + productId + '\'' +
				", merchantId='" + merchantId + '\'' +
				'}';
	}
}
