package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Merchant implements Serializable

{
	@SerializedName("merchantId")
	private String  merchantId;
	private String productId;
	private Integer price;
	private Double rating;
	private Integer stock;
	private String merchantName;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}



	@Override
	public String toString() {
		return "Merchant{" +
				"merchantId='" + merchantId + '\'' +
				", productId='" + productId + '\'' +
				", price=" + price +
				", rating=" + rating +
				", stock=" + stock +
				", merchantName='" + merchantName + '\'' +
				'}';
	}
}
