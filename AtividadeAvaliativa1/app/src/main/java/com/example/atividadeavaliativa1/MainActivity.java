package com.example.atividadeavaliativa1;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.atividadeavaliativa1.ui.home.HomeFragment;
import com.example.atividadeavaliativa1.user.UserDao;
import com.example.atividadeavaliativa1.user.UserDatabase;
import com.example.atividadeavaliativa1.user.UserEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.atividadeavaliativa1.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_tickets, R.id.navigation_chat, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    //Navegação entre os fragments do menu principal
    public boolean onMenuOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_map) {
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_map);
            return true;
        }
        else if(id == R.id.navigation_home) {
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_activity_main);
            if (navController.getCurrentDestination().getId() == R.id.navigation_home)
                Toast.makeText(this, "Already in first.", Toast.LENGTH_SHORT).show();
            else
                navController.navigate(R.id.navigation_home);
            return true;
        }
        else if(id == R.id.navigation_tickets) {
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_tickets);

            return true;
        }
        else if(id == R.id.navigation_chat) {
            Toast.makeText(this, "selecionou chat", Toast.LENGTH_SHORT).show();
            /*NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_chat);*/
            return true;
        }
        else if(id == R.id.navigation_profile) {
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_profile);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Navegação entre os fragments do menu do perfil
    public boolean onMenuProfileOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_tickets) {
            NavController navController = Navigation
                    .findNavController(this, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_tickets);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // trata o up and back button
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}