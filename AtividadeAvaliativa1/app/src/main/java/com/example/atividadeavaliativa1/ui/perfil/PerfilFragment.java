package com.example.atividadeavaliativa1.ui.perfil;



import static com.example.atividadeavaliativa1.user.Activity_login.USER_FILE_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.databinding.FragmentPerfilBinding;
import com.example.atividadeavaliativa1.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textPerfil;

        SharedPreferences preferences = this.getActivity().getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        String display = preferences.getString("name", this.getActivity().getResources().getString(R.string.user_name));
        final TextView textView = binding.textPerfil;
        textView.setText(display);

        if(preferences.contains("email") && preferences.contains("password")){
            binding.buttonLogout.setText(this.getActivity().getResources().getString(R.string.logout));
        }
        else{
            binding.buttonLogout.setText(this.getActivity().getResources().getString(R.string.login));
        }

        binding.buttonLogout.setOnClickListener(v -> {
            if(preferences.contains("email") && preferences.contains("password")) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", null);
                editor.putString("email", null);
                editor.putString("password", null);
                editor.commit();
                editor.apply();
                textView.setText(this.getActivity().getResources().getString(R.string.user_name));
                binding.buttonLogout.setText(this.getActivity().getResources().getString(R.string.login));
            } else{
                NavHostFragment.findNavController(PerfilFragment.this)
                        .navigate(R.id.action_navigation_profile_to_activity_login);
            }
        });

       /* binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*NavHostFragment.findNavController(PerfilFragment.this)
                        .navigate(R.id.action_navigation_home_to_cadastroEventoActivity);*//*

                if(preferences.contains("email") && preferences.contains("password")){
                    *//*binding.buttonLogout.setText(view.getResources().getString(R.string.login));
                    NavHostFragment.findNavController(PerfilFragment.this)
                            .navigate(R.id.action_navigation_profile_to_activity_login);*//*
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", "");
                    editor.putString("email", "");
                    editor.putString("password", "");
                    editor.commit();
                    editor.apply();
                }
                else{
                    NavHostFragment.findNavController(PerfilFragment.this)
                            .navigate(R.id.action_navigation_profile_to_activity_login);
                    *//*binding.buttonLogout.setText(view.getResources().getString(R.string.logout));*//*
                }

            }
        });*/



        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationView navigationView = view.findViewById(R.id.list_nav);
        NavigationUI.setupWithNavController(binding.listNav, navController);
      //  perfilViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}