package com.example.projetonotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passar_tela();
    }

    private void passar_tela(){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent login = new Intent(MainActivity.this, Tela_Admin.class);
            finish();
            startActivity(login);
        }, 5000);
    }
}