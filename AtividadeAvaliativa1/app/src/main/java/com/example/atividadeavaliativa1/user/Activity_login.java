package com.example.atividadeavaliativa1.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.data.GeneralDatabase;
import com.example.atividadeavaliativa1.data.user.UserDao;
import com.example.atividadeavaliativa1.data.user.UserEntity;

public class Activity_login extends AppCompatActivity {

    EditText userId, password;
    Button login;

    TextView register;

    ImageView btnClose;
    public static String USER_FILE_NAME = "user_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        btnClose = findViewById(R.id.button_close_login);
        SharedPreferences preferences = getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha Todos os Campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform Query
                    GeneralDatabase generalDatabase = GeneralDatabase.getInstance(getApplicationContext());
                    UserDao userDao = generalDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if (userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Credênciais Inválidas", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {

                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("name", userEntity.getName());
                                editor.putString("email", userEntity.getUserId());
                                editor.putString("password", userEntity.getPassword());
                                editor.commit();
                                editor.apply();
                                startActivity(new Intent(
                                       Activity_login.this, MainActivity.class));
                            }
                        }
                    }).start();
                }
            }
        });

        register.setOnClickListener(view -> {
            Intent i = new Intent(Activity_login.this, Activity_cadastro.class);
            startActivity(i);
        });

        btnClose.setOnClickListener(view -> {
            Intent i = new Intent(Activity_login.this, MainActivity.class);
            startActivity(i);
        });

    }

}