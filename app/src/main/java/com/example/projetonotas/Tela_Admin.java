package com.example.projetonotas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.projetonotas.ViewTeacher.Tela_Inicial;
import com.google.firebase.auth.FirebaseAuth;

public class Tela_Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_admin);

        View professor = findViewById(R.id.Professor);
        View aluno = findViewById(R.id.Aluno);
        @SuppressLint("WrongConstant") SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_ENABLE_WRITE_AHEAD_LOGGING).edit();
        SessaoLogado();

        professor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user","teacher");
                editor.apply();
                passar_tela();
            }
        });

        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user","student");
                editor.apply();
                passar_tela();
            }
        });
    }

    private void passar_tela() {
        Intent login = new Intent(Tela_Admin.this, TelaLogin.class);
        finish();
        startActivity(login);
    }

    private void SessaoLogado(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
            Intent login = new Intent(Tela_Admin.this, Tela_Inicial.class);
            finish();
            startActivity(login);
        }
    }
}