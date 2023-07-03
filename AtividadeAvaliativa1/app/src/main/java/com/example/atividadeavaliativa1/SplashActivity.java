package com.example.atividadeavaliativa1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atividadeavaliativa1.ui.perfil.PerfilFragment;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        pref = getSharedPreferences("user_info",MODE_PRIVATE);
        boolean isUserLogged = pref.getBoolean("user_logged", false);

        startActivity(new Intent(this, isUserLogged ? MainActivity.class : MainActivity.class));
        finish();
    }
}
