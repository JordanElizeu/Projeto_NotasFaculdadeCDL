package com.example.projetonotas.ViewTeacher;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;

import com.example.projetonotas.R;
import com.example.projetonotas.Tela_Admin;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetonotas.databinding.ActivityTelaInicialBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Tela_Inicial extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityTelaInicialBinding binding;
    private AlertDialog alert;
    private AlertDialog.Builder alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTelaInicialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarTelaInicial.toolbar);
        alerta = new AlertDialog.Builder(Tela_Inicial.this);

        @SuppressLint("WrongConstant") SharedPreferences pref = getSharedPreferences("pref", MODE_ENABLE_WRITE_AHEAD_LOGGING);
        String user = pref.getString("user",null);

        if (user.equals("student")){

            binding.drawerLayout.findViewById(R.id.nav_gallery).setVisibility(View.GONE);
            binding.drawerLayout.findViewById(R.id.nav_home).setVisibility(View.GONE);

        }else if (user.equals("teacher")){
            binding.drawerLayout.findViewById(R.id.nav_gallery).setVisibility(View.VISIBLE);
            binding.drawerLayout.findViewById(R.id.nav_home).setVisibility(View.VISIBLE);

            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_tela_inicial);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela__inicial, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_tela_inicial);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Logout(MenuItem item) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent login = new Intent(Tela_Inicial.this, Tela_Admin.class);
        finish();
        startActivity(login);
    }

    @Override
    public void onBackPressed() {
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setTitle("Sair da conta ?");
        //define um botão como positivo
        alerta.setPositiveButton("Confirmar", (arg0, arg1) -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            Intent login = new Intent(Tela_Inicial.this, Tela_Admin.class);
            finish();
            startActivity(login);
        });
        //define um botão como negativo.
        alerta.setNegativeButton("Cancelar", (arg0, arg1) -> {
        });
        //cria o AlertDialog
        alert = alerta.create();
        //Exibe
        alert.show();
        super.onBackPressed();
    }
}