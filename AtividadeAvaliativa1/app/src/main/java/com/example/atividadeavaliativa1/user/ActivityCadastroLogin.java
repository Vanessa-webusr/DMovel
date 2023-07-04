package com.example.atividadeavaliativa1.user;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.databinding.ActivityCadastroLoginBinding;
import com.example.atividadeavaliativa1.databinding.ActivityMainBinding;

import android.content.Intent;
import android.content.SharedPreferences;

public class ActivityCadastroLogin extends AppCompatActivity {
    /*SharedPreferences pref;*/

    private ActivityCadastroLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonRegister.setOnClickListener(view -> {
            Intent i = new Intent(ActivityCadastroLogin.this, Activity_cadastro.class);
            startActivity(i);
        });

        binding.textLogin.setOnClickListener(view -> {
            Intent i = new Intent(ActivityCadastroLogin.this, Activity_login.class);
            startActivity(i);
        });
/*
        binding.exitButton.setOnClickListener(view -> {
            MainActivity.this.finish();
        });*/

        binding.buttonClose.setOnClickListener(view -> {
            Intent i = new Intent(ActivityCadastroLogin.this, MainActivity.class);
            startActivity(i);
        });

    }
}