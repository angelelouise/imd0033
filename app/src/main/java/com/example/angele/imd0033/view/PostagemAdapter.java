package com.example.angele.imd0033.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.angele.imd0033.R;
import com.example.angele.imd0033.dominio.Postagem;

import java.util.List;

public class PostagemAdapter extends RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder> {
    private List<Postagem> mPostagems;

    public PostagemAdapter(List<Postagem> mPostagems) {
        this.mPostagems = mPostagems;
    }

    @NonNull
    @Override
    public PostagemAdapter.PostagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_postagem, parent, false);
        return new PostagemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostagemAdapter.PostagemViewHolder holder, int position) {
        Postagem post = mPostagems.get(position);
        holder.viewAutor.setText(post.getUsuario());
        holder.viewComponente.setText(post.getId_componente_curricular().toString());
        holder.viewTitulo.setText(post.getTitulo());
        holder.viewDescricao.setText(post.getDescricao());
    }

    @Override
    public int getItemCount() {
        return mPostagems.size();
    }

    public class PostagemViewHolder extends RecyclerView.ViewHolder {
        protected TextView viewTitulo;
        protected TextView viewDescricao;
        protected TextView viewAutor;
        protected TextView viewComponente;
        protected ImageButton viewShare;

        public PostagemViewHolder(View itemView) {
            super(itemView);
            //bind elements

            viewTitulo = itemView.findViewById(R.id.textTitulo);
            viewDescricao = itemView.findViewById(R.id.textDescricao);
            //viewComponente = itemView.findViewById();
            viewAutor = itemView.findViewById(R.id.textAutor);
            viewShare= itemView.findViewById(R.id.imageButtonShare);
        }
    }
}

