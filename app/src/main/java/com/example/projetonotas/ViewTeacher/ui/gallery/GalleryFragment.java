package com.example.projetonotas.ViewTeacher.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetonotas.ViewTeacher.Tela_Inicial;
import com.example.projetonotas.databinding.FragmentGalleryBinding;

import java.text.DecimalFormat;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final EditText nome = binding.nome;
        final EditText nota1 = binding.nota1;
        final EditText nota2 = binding.nota2;
        final EditText nota3 = binding.nota3;
        final EditText nota4 = binding.nota4;
        final Button cadastrar = binding.buttonCadastrar;

        @SuppressLint("WrongConstant") SharedPreferences.Editor editor = getActivity().getSharedPreferences("pref", getActivity().MODE_ENABLE_WRITE_AHEAD_LOGGING).edit();
        @SuppressLint("WrongConstant") SharedPreferences pref = getActivity().getSharedPreferences("pref", getActivity().MODE_ENABLE_WRITE_AHEAD_LOGGING);

        if (pref.getString("quantidade",null) == null){
            editor.putString("quantidade","0");
            editor.apply();
        }

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat f = new DecimalFormat("#.#");

                final String nota1Mask = f.format(Double.parseDouble(nota1.getText().toString()));
                final String nota2Mask = f.format(Double.parseDouble(nota2.getText().toString()));
                final String nota3Mask = f.format(Double.parseDouble(nota3.getText().toString()));
                final String nota4Mask = f.format(Double.parseDouble(nota4.getText().toString()));

                if (nome.getText().length() > 0 && nota1.getText().length() > 0 && nota2.getText().length() > 0 && nota3.getText().length() > 0 && nota4.getText().length() > 0) {
                    int quantidade = Integer.parseInt(pref.getString("quantidade", null));
                    editor.putString("aluno" + quantidade, nome.getText().toString());
                    editor.putString("nota1" + quantidade, nota1Mask);
                    editor.putString("nota2" + quantidade, nota2Mask);
                    editor.putString("nota3" + quantidade, nota3Mask);
                    editor.putString("nota4" + quantidade, nota4Mask);
                    editor.putString("quantidade", String.valueOf(quantidade+1));
                    editor.apply();
                    Toast.makeText(getContext(), "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    nome.setText("");
                    nota1.setText("");
                    nota2.setText("");
                    nota3.setText("");
                    nota4.setText("");
                    Intent TelaPrincipal = new Intent(getContext(), Tela_Inicial.class);
                    startActivity(TelaPrincipal);
                    try {
                        finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }else {
                    Toast.makeText(getContext(), "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}