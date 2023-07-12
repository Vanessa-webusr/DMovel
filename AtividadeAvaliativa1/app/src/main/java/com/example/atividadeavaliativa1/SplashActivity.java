package com.example.atividadeavaliativa1;

import static com.example.atividadeavaliativa1.user.Activity_login.USER_FILE_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atividadeavaliativa1.ui.perfil.PerfilFragment;

public class SplashActivity extends AppCompatActivity {
    //SharedPreferences pref;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        SharedPreferences preferences = getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);

        if(preferences.contains("email") && preferences.contains("password")){
            startActivity(new Intent(this,MainActivity.class));
        }
        else{
            startActivity(new Intent(this, com.example.atividadeavaliativa1.user.ActivityCadastroLogin.class));
        }
        finish();
    }
}
