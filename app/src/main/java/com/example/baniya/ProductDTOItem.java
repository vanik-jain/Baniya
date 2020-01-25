package com.example.baniya;


import com.google.gson.annotations.SerializedName;


public class ProductDTOItem{

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private int price;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("counter")
	private int counter;

	@SerializedName("stock")
	private int stock;

	@SerializedName("productName")
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

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
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