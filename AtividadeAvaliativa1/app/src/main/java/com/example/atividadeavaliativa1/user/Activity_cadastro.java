package com.example.atividadeavaliativa1.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.data.GeneralDatabase;
import com.example.atividadeavaliativa1.data.user.UserDao;
import com.example.atividadeavaliativa1.data.user.UserEntity;

public class Activity_cadastro extends AppCompatActivity {

    EditText userId, password, name;
    Button register;
    TextView login;

    ImageView btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        //CADASTRO
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        btnClose = findViewById(R.id.button_close_cad);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creating User Entity
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userId.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());

                if (validateInput(userEntity)) {

                    //Do insert operation
                    GeneralDatabase generalDatabase = GeneralDatabase.getInstance(getApplicationContext());
                    UserDao userDao = generalDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            // Register User
                            userDao.registerUser(userEntity);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.register_user_sucess), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.fill_incomplete) , Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener( view -> {
            Intent i = new Intent(Activity_cadastro.this, Activity_login.class);
            startActivity(i);
        });

        btnClose.setOnClickListener(view -> {
            Intent i = new Intent(Activity_cadastro.this, MainActivity.class);
            startActivity(i);
        });
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getName().isEmpty() ||
                userEntity.getPassword().isEmpty() ||
                userEntity.getName().isEmpty()) {
            return  false;
        }
        return true;
    }
}