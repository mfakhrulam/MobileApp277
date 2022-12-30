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

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText inpName, inpNim, inpPassword;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.btn_register = this.findViewById(R.id.btn_reg);

        this.inpName = this.findViewById(R.id.inp_name);
        this.inpNim = this.findViewById(R.id.inp_nim);
        this.inpPassword = this.findViewById(R.id.inp_password);

        this.btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = auth();
                if(valid){
                    UserDao daoUser = AppDBProvider.getInstance(getApplicationContext()).userDao();
                    daoUser.insertAll(makeUser());
                    Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
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

    private boolean auth()
    {
        // error jika nim, nama, password kosong
        // error jika nim sudah ada

        String currentNim = this.inpNim.getText().toString().trim();
        String currentName = this.inpName.getText().toString().trim();
        String currentPassword = this.inpPassword.getText().toString().trim();

        if(currentName.isEmpty())
            Toast.makeText(this, "Nama harus diisi", Toast.LENGTH_SHORT).show();
        else if(currentNim.isEmpty())
            Toast.makeText(this, "NIM harus diisi", Toast.LENGTH_SHORT).show();
        else if(currentPassword.isEmpty())
            Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show();
        else if(currentPassword.length() < 8)
            Toast.makeText(this, "Password minimum memiliki 8 karakter", Toast.LENGTH_SHORT).show();
        else
        {
            UserDao daoUser = AppDBProvider.getInstance(this).userDao();
            User currentUser = daoUser.findByNim(currentNim);
            if (currentUser != null)
                Toast.makeText(this, "User dengan NIM sama sudah terdaftar", Toast.LENGTH_SHORT).show();
            else
                return true;
        }

        return false;
    }
}