package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ViewCartDTO implements Serializable
{
	@SerializedName("cartId")
	private String cartId;
	private List<ProductDTOItem> productDTO;
	private Double total;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	public void setProductDTO(List<ProductDTOItem> productDTO){
		this.productDTO = productDTO;
	}

	public List<ProductDTOItem> getProductDTO(){
		return productDTO;
	}

	@Override
	public String toString() {
		return "ViewCartDTO{" +
				"cartId='" + cartId + '\'' +
				", productDTO=" + productDTO +
				", total=" + total +
				'}';
	}
}