package com.example.baniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity
{
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar actionBar = getActionBar();
//        if (actionBar != null)
//        actionBar.hide();





        new Handler().postDelayed
                (new Runnable() {
                    @Override
                    public void run()
                    {
                            Intent intent = new Intent(MainActivity.this, LandingActivity.class);
                            startActivity(intent);

                        finish();
                    }
                }, 700);

    }
}
