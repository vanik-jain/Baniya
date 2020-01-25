package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class Product implements Serializable
{

	@SerializedName("productId")
	private String productId;
	private String imageUrl;
	private double productRating;
	private String productName;
	private String productDescription;
	private String categoryId;
	private Map productAttribute;
	private String productUsp;
	private double price;
	private String merchantId;
	private int stock;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
//private double weighted;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setProductRating(double productRating){
		this.productRating = productRating;
	}

	public double getProductRating(){
		return productRating;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductDescription(String productDescription){
		this.productDescription =  productDescription;
	}

	public String getProductDescription(){
		return productDescription;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setProductAttribute(Map productAttribute)
	{
		this.productAttribute = productAttribute;
	}

	public Object getProductAttribute(){
		return productAttribute;
	}

	public void setProductUsp(String productUsp){
		this.productUsp = productUsp;
	}

	public String getProductUsp(){
		return productUsp;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId='" + productId + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", productRating=" + productRating +
				", productName='" + productName + '\'' +
				", productDescription='" + productDescription + '\'' +
				", categoryId='" + categoryId + '\'' +
				", productAttribute=" + productAttribute +
				", productUsp='" + productUsp + '\'' +
				", price=" + price +
				", merchantId='" + merchantId + '\'' +
				", stock=" + stock +
				'}';
	}
}
