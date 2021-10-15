package com.example.projetonotas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetonotas.ViewTeacher.Tela_Inicial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class TelaLogin extends AppCompatActivity {

    private EditText User, Password;
    private String sessao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        User = findViewById(R.id.UserText);
        Password = findViewById(R.id.PasswordText);
        Button Login = findViewById(R.id.Button_Entrar);
        TextView esqueciSenha = findViewById(R.id.EsqueciSenha);
        @SuppressLint("WrongConstant") SharedPreferences pref = getSharedPreferences("pref", MODE_ENABLE_WRITE_AHEAD_LOGGING);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = pref.getString("user",null);
                if (user.equals("teacher")){
                    TypeTeacher();
                }else if(user.equals("student")){
                    TypeStudent();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent login = new Intent(TelaLogin.this, Tela_Admin.class);
        finish();
        startActivity(login);
        super.onBackPressed();
    }

    private void LoginSessao() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (User.getText().length() > 0 && Password.getText().length() > 0) {
            auth.signInWithEmailAndPassword(User.getText().toString(), Password.getText().toString())
                    .addOnCompleteListener(TelaLogin.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            auth.signInWithEmailAndPassword(User.getText().toString(), Password.getText().toString());
                            Intent login = new Intent(TelaLogin.this, Tela_Inicial.class);
                            finish();
                            startActivity(login);
                        }
                    });
        } else {
            Toast.makeText(TelaLogin.this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void TypeTeacher() {
        DatabaseReference banco;
        banco = FirebaseDatabase.getInstance().getReference("Professores");

        banco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                if (snapshot.child("email").getValue() != null) {
                    sessao = "true";
                    String email = snapshot.child("email").getValue().toString();
                    if (User.getText().toString().equals(email)) {
                        LoginSessao();
                    }
                }
                if (sessao == null && snapshot.child("email").getValue() == null){
                    Toast.makeText(TelaLogin.this, "Login falhou! tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void TypeStudent() {
        DatabaseReference banco;
        banco = FirebaseDatabase.getInstance().getReference("Estudantes");

        banco.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                if (snapshot.child("email").getValue() != null) {
                    sessao = "true";
                    String email = snapshot.child("email").getValue().toString();
                    if (User.getText().toString().equals(email)) {
                        LoginSessao();
                    }
                }
                if (sessao == null && snapshot.child("email").getValue() == null){
                    Toast.makeText(TelaLogin.this, "Login falhou! tente novamente mais tarde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}