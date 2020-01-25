package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequestDTO implements Serializable
{
	@SerializedName("email")
	private String email;
	private String password;
	private String role="user";


	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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
			"LoginRequestDTO{" + 
			"password = '" + password + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
