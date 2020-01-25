package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignUpRequestDTO implements Serializable
{
	@SerializedName("email")
	private String email;
	private String password;
	private String name;
	private String role="user";


	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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
			"SignUpRequestDTO{" + 
			"password = '" + password + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
