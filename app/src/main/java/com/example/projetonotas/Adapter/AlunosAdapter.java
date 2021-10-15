package com.example.projetonotas.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetonotas.Model.AlunosModel;
import com.example.projetonotas.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AlunosAdapter {

    public static class TypeAdapter extends RecyclerView.Adapter<AlunosAdapter.TypeAdapter.ViewHolder> {

        private final ArrayList<AlunosModel> contactos3;
        private Context context;

        public TypeAdapter(Context context,ArrayList<AlunosModel> contactos) {
            this.contactos3 = contactos;
            this.context = context;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView nome;
            public TextView nota1;
            public TextView nota2;
            public TextView nota3;
            public TextView nota4;

            public ViewHolder(View vCard) {
                super(vCard);
                nome = vCard.findViewById(R.id.nomeModel);
                nota1 = vCard.findViewById(R.id.nota1Model);
                nota2 = vCard.findViewById(R.id.nota2Model);
                nota3 = vCard.findViewById(R.id.nota3Model);
                nota4 = vCard.findViewById(R.id.nota4Model);
            }
        }
        @Override
        public AlunosAdapter.TypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notasmodel, parent, false);
            // set the view's size, margins, paddings and layout parameters
            //...
            return new ViewHolder(v);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

            AlunosModel item = contactos3.get(position);

            holder.nome.setText(item.getNome());
            holder.nota1.setText(item.getNota1());
            holder.nota2.setText(item.getNota2());
            holder.nota3.setText(item.getNota3());
            holder.nota4.setText(item.getNota4());

        }

        @Override
        public int getItemCount() {
            return contactos3.size();
        }

        private void delete(int position){
            contactos3.remove(position);
            notifyItemRemoved(position);
        }

        public void update(AlunosModel contactos, int position){
            contactos3.set(position, contactos);
            notifyItemChanged(position);
        }

        public void insere(ArrayList<AlunosModel> contactos){
            contactos3.addAll(contactos);
            notifyDataSetChanged();
        }
    }
}
