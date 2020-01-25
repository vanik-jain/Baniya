package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity
{

  private   EditText emailEditext;
  private   EditText passwordEditText;
  private   EditText cpassword;
  private   EditText nameEditText;
  private LinearLayout progressBarLinearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

      emailEditext = findViewById(R.id.email);
      passwordEditText = findViewById(R.id.password);
      nameEditText = findViewById(R.id.name);
      cpassword = findViewById(R.id.cpassword);
      progressBarLinearLayout = findViewById(R.id.progressbar_layout);

    }

    public void SignUp(View view)
    {
            progressBarLinearLayout.setVisibility(View.VISIBLE);
            Api api = App.getRetrofit().create(Api.class);
             SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
            signUpRequestDTO.setEmail(emailEditext.getText().toString());
            signUpRequestDTO.setName(nameEditText.getText().toString());
            signUpRequestDTO.setPassword(passwordEditText.getText().toString());

        Call<SignUpResponseDTO> call = api.sendSignupCredentials(signUpRequestDTO);

        call.enqueue(new Callback<SignUpResponseDTO>() {
          @Override
          public void onResponse(Call<SignUpResponseDTO> call, Response<SignUpResponseDTO> response)
          {
                 if (response.body() != null)
                 {
                   SignUpResponseDTO signUpResponseDTO = response.body();
                   if(signUpResponseDTO.isSuccess())
                   {
                       progressBarLinearLayout.setVisibility(View.GONE);
                       Toast.makeText(SignUp.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(SignUp.this,LoginActivity.class));
                       finish();
                   }
                 }
          }

          @Override
          public void onFailure(Call<SignUpResponseDTO> call, Throwable t)
          {
              progressBarLinearLayout.setVisibility(View.GONE);
            Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
          }
        });

//      startActivity(new Intent(SignUp.this,LoginActivity.class));
//      finish();


      }



    public void Login(View view)
    {
      startActivity(new Intent(SignUp.this,LoginActivity.class));
      finish();
    }
}
