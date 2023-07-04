package com.example.atividadeavaliativa1.user;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;

import android.content.Intent;
import android.content.SharedPreferences;

public class ActivityCadastroLogin extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);

        pref = getSharedPreferences("user_info",MODE_PRIVATE);
        boolean isUserLogged = pref.getBoolean("user_logged", false);

        startActivity(new Intent(this, isUserLogged ? MainActivity.class : Activity_login.class));
        finish();

    }
}