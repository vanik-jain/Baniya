package com.example.baniya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN =1 ;
    private EditText mUserEmail;
    private EditText mUserPassword;
    private SharedPreferences sharedPreferences;
    private GoogleSignInClient googleSignInClient;
    private LinearLayout progressBarLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserEmail = findViewById(R.id.username);
        mUserPassword = findViewById(R.id.password);
        progressBarLinearLayout = findViewById(R.id.progressbar_layout);

        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);



    }

    public void SignUp(View view)
    {
        startActivity(new Intent(LoginActivity.this, SignUp.class));
        finish();
    }

    public void login(View view) {

        if (!(mUserEmail.getText().toString().equals("") || mUserPassword.getText().toString().equals("")))
        {
            progressBarLinearLayout.setVisibility(View.VISIBLE);
            Api api = App.getRetrofit() .create(Api.class);
            final LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
            loginRequestDTO.setEmail(mUserEmail.getText().toString());
            loginRequestDTO.setPassword(mUserPassword.getText().toString());

            Call<LoginResponseDTO> call = api.sendLoginCredentials(loginRequestDTO,null,"local","false");
            call.enqueue(new Callback<LoginResponseDTO>()
            {
                @Override
                public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response)
                {
                    if (response.body() != null)
                    {
                        progressBarLinearLayout.setVisibility(View.GONE);
                        LoginResponseDTO loginResponseDTO = response.body();
                        String auth_token =loginResponseDTO.getTokenType()+" "+loginResponseDTO.getAccessToken();
                        if (!auth_token.equals(""))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("auth_token", auth_token);
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,LandingActivity.class));
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseDTO> call, Throwable t)
                {
                    progressBarLinearLayout.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        else
            {

            Toast.makeText(LoginActivity.this, "Login or Password can't be blank!", Toast.LENGTH_SHORT).show();
             }
    }

    public void googleSignIn(View view)
    {
        progressBarLinearLayout.setVisibility(View.VISIBLE);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result)
    {
        if (result.isSuccess())
        {
            GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
            String idToken = googleSignInAccount.getIdToken();
            Api api = App.getRetrofit().create(Api.class);
            LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
            Call<LoginResponseDTO> call = api.sendLoginCredentials(loginRequestDTO,idToken,"google","false");
            call.enqueue(new Callback<LoginResponseDTO>() {
                @Override
                public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response)
                {
                    if (response.body() != null)
                    {
                        progressBarLinearLayout.setVisibility(View.GONE);
                        LoginResponseDTO loginResponseDTO = response.body();
                        String auth_token =loginResponseDTO.getTokenType()+" "+loginResponseDTO.getAccessToken();
                        if (!auth_token.equals(""))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("auth_token", auth_token);
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,LandingActivity.class));
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseDTO> call, Throwable t)
                {
                    progressBarLinearLayout.setVisibility(View.GONE);
                   Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });



//            Log.i("VANIK",idToken);
        }
    }
}