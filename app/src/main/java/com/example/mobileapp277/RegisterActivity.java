package com.example.mobileapp277;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapp277.database.AppDBProvider;
import com.example.mobileapp277.database.User;
import com.example.mobileapp277.database.UserDao;

public class RegisterActivity extends AppCompatActivity {

    private EditText inpName, inpNim, inpPassword;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.btn_register = this.findViewById(R.id.btn_logout);

        this.inpName = this.findViewById(R.id.inp_name);
        this.inpNim = this.findViewById(R.id.inp_nim);
        this.inpPassword = this.findViewById(R.id.inp_password);

        this.btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDao daoUser = AppDBProvider.getInstance(getApplicationContext()).userDao();
                daoUser.insertAll(makeUser());
                Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private User makeUser()
    {
        User newUser = new User();
        newUser.nim = this.inpNim.getText().toString().trim();
        newUser.name = this.inpName.getText().toString().trim();
        newUser.password = this.inpPassword.getText().toString().trim();

        return newUser;
    }
}