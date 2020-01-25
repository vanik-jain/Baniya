package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDetailsDTO implements Serializable
{
	@SerializedName("role")
	private String role;
	private String provider;
	private Object providerId;
	private String imageUrl;
	private String name;
	private String id;
	private String email;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setProvider(String provider){
		this.provider = provider;
	}

	public String getProvider(){
		return provider;
	}

	public void setProviderId(Object providerId){
		this.providerId = providerId;
	}

	public Object getProviderId(){
		return providerId;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"UserDetailsDTO{" + 
			"role = '" + role + '\'' + 
			",provider = '" + provider + '\'' + 
			",providerId = '" + providerId + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
