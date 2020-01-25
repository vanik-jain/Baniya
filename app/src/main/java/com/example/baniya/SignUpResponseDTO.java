package com.example.baniya;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignUpResponseDTO implements Serializable
{
	@SerializedName("success")
	private boolean success;
	private String message;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"SignUpResponseDTO{" +
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
