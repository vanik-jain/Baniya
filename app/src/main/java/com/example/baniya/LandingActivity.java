package com.example.baniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class LandingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawerLayout;

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

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);

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
