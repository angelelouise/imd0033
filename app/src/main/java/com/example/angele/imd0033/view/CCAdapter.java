package com.example.angele.imd0033.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.angele.imd0033.R;
import com.example.angele.imd0033.dominio.ComponenteCurricular;

import java.util.List;

public class CCAdapter extends RecyclerView.Adapter<CCAdapter.CCViewHolder> {

    private List<ComponenteCurricular> CC;

    public CCAdapter(List<ComponenteCurricular> CC) {
        this.CC = CC;
    }

    @NonNull
    @Override
    public CCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cc, parent, false);
        return new CCViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CCViewHolder holder, int position) {
        ComponenteCurricular cc = CC.get(position);
        holder.view_id_componente.setText(cc.getId_componente());
        holder.view_codigo.setText(cc.getCodigo());
        holder.view_nome.setText(cc.getNome());
    }

    @Override
    public int getItemCount() {
        return CC.size();
    }

    public class CCViewHolder extends RecyclerView.ViewHolder{

        private TextView view_carga_horaria_total;
        private TextView view_co_requisitos;
        private TextView view_codigo;
        private TextView view_componentesBloco;
        private TextView view_departamento;
        private TextView view_disciplina_obrigatoria;
        private TextView view_equivalentes;
        private TextView view_id_componente;
        private TextView view_id_matriz_curricular;
        private TextView view_nome;
        private TextView view_pre_requisitos;
        private TextView view_semestre_oferta;

        public CCViewHolder(View itemView) {
            super(itemView);

            view_nome.findViewById(R.id.text_nome);
            view_codigo.findViewById(R.id.text_codigo);
            view_id_componente.findViewById(R.id.text_id);
        }


    }

}
