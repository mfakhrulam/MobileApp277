package com.example.mobileapp277;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String DUMMY_NIM = "2000018277";
    private static final String DUMMY_PASSWORD = "asdasd";
    private static final String AUTO_LOGIN_KEY = "key_auto_login";

    private SharedPreferences sharedPrefs;

    private Button btnLogin, btnRegister;
    private EditText inpNim, inpPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    makeAutoLogin();
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Nim dan/atau password tidak valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        autoLogin();
    }

    private boolean auth()
    {
        String currentNim = this.inpNim.getText().toString();
        String currentPassword = this.inpPassword.getText().toString();

        return (Objects.equals(currentNim, DUMMY_NIM) &&
                Objects.equals(currentPassword, DUMMY_PASSWORD));
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
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }


}