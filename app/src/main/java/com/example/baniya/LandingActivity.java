package com.example.baniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;
    private String authToken;
    private UserDetailsDTO userDetailsDTO;

   private ImageView imageViewDp;
   private TextView textViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        final NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
        authToken = sharedPreferences.getString("auth_token",null);
        Api api = App.getRetrofit().create(Api.class);
        Call<UserDetailsDTO> call = api.getUserDetails(authToken);
        call.enqueue(new Callback<UserDetailsDTO>() {
            @Override
            public void onResponse(Call<UserDetailsDTO> call, Response<UserDetailsDTO> response)
            {

                if (response.body() != null)
                {
                    userDetailsDTO = response.body();
                    View headerView = navigationView.getHeaderView(0);
                    textViewUsername = headerView.findViewById(R.id.nav_header_name);
                    imageViewDp = headerView.findViewById(R.id.nav_header_image);
                    textViewUsername.setText(userDetailsDTO.getName());
                    Glide.with(imageViewDp.getContext())
                            .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                            .load(userDetailsDTO.getImageUrl()).into(imageViewDp);
                    Log.i("VANIK4",userDetailsDTO.toString());
                }
            }

            @Override
            public void onFailure(Call<UserDetailsDTO> call, Throwable t)
            {
                Toast.makeText(LandingActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i("VANIK4",t.getMessage());



                //textViewUsername = headerView.findViewById(R.id.textViewUsername);



            }
        });



    }


    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else
            {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {

        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LandingFragment("landing")).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_mobile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LandingFragment("1")).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_tablet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LandingFragment("2")).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_laptop:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LandingFragment("3")).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_watch:
                drawerLayout.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LandingFragment("4")).commit();
                break;
            case R.id.nav_headphones:
                drawerLayout.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LandingFragment("5")).commit();
                break;
            case R.id.nav_my_orders:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_login:
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onSearchRequested() {
       // pauseSomeStuff();
        return super.onSearchRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search_menu_item_id:
                onSearchRequested();
                return true;
            case R.id.cart_menu_item_id:
                startActivity(new Intent(LandingActivity.this,CartActivity.class ));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
