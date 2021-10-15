package com.example.projetonotas.ViewTeacher.ui.home;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetonotas.Adapter.AlunosAdapter;
import com.example.projetonotas.Model.AlunosModel;
import com.example.projetonotas.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private AlunosAdapter.TypeAdapter adapter;
    private final ArrayList<AlunosModel> NotasAlunos = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView notas = binding.recycleNotas;
        @SuppressLint("WrongConstant") SharedPreferences pref = getActivity().getSharedPreferences("pref", getActivity().MODE_ENABLE_WRITE_AHEAD_LOGGING);

        notas.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 1);
        notas.setLayoutManager(mLayoutManager2);

        if (pref.getString("quantidade",null) != null){
            int quantidade = Integer.parseInt(pref.getString("quantidade",null));

            for (int x = 0; x < quantidade; x++){
                String nome = pref.getString("aluno"+x,null);
                String nota1 = pref.getString("nota1"+x,null);
                String nota2 = pref.getString("nota2"+x,null);
                String nota3 = pref.getString("nota3"+x,null);
                String nota4 = pref.getString("nota4"+x,null);


                NotasAlunos.add(new AlunosModel(nome,nota1,nota2,nota3,nota4));
                adapter = new AlunosAdapter.TypeAdapter(getContext(), NotasAlunos);
                notas.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}