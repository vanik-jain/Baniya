package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductDTOItem implements Serializable
{
	@SerializedName("productId")
	private String productId;
	private String merchantId;
	private double price;
	private String imageUrl;
	private int counter;
	private int stock;
	private String productName;

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

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setCounter(int counter){
		this.counter = counter;
	}

	public int getCounter(){
		return counter;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"ProductDTOItem{" + 
			"productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",price = '" + price + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",counter = '" + counter + '\'' + 
			",stock = '" + stock + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}
