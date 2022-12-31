package com.example.mobileapp277;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapp277.database.AppDBProvider;
import com.example.mobileapp277.database.User;
import com.example.mobileapp277.database.UserDao;

public class LoginActivity extends AppCompatActivity {
    private static final String AUTO_LOGIN_KEY = "key_auto_login";

    private SharedPreferences sharedPrefs;

    private Button btnLogin, btnRegister;
    private EditText inpNim, inpPassword;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefs = getSharedPreferences("mobileapp277_sharedprefs", Context.MODE_PRIVATE);

        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_reg);
        inpNim = findViewById(R.id.inp_nim);
        inpPassword = findViewById(R.id.inp_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = auth();

                if (valid)
                {
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                    makeAutoLogin();
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Nim dan/atau password tidak valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        autoLogin();
    }


    private boolean auth()
    {
        String currentNim = this.inpNim.getText().toString();
        String currentPassword = this.inpPassword.getText().toString();

        UserDao daoUser = AppDBProvider.getInstance(this).userDao();
        currentUser = daoUser.findByNimAndPassword(currentNim, currentPassword);

        return currentUser!=null?true:false;
    }

    private void makeAutoLogin()
    {
        SharedPreferences.Editor editor = this.sharedPrefs.edit();
        editor.putBoolean(AUTO_LOGIN_KEY, true);
        editor.apply();
    }

    private void autoLogin()
    {
        boolean auto = this.sharedPrefs.getBoolean(AUTO_LOGIN_KEY, false);
        if (auto)
        {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }


}