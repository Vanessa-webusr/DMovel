package com.example.atividadeavaliativa1.ui.perfil;



import static com.example.atividadeavaliativa1.user.Activity_login.USER_FILE_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.databinding.FragmentPerfilBinding;
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

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationView navigationView = view.findViewById(R.id.list_nav);
        NavigationUI.setupWithNavController(binding.listNav, navController);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        String display = preferences.getString("name", "");
        final TextView textView = binding.textPerfil;
        textView.setText(display);


      //  perfilViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("FirstFragment", "onDestroyView");
        binding = null;
    }

}