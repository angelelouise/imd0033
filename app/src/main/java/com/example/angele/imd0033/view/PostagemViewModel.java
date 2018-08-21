package com.example.angele.imd0033.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.angele.imd0033.bd.PostagemRepository;
import com.example.angele.imd0033.dominio.Postagem;

import java.util.List;

/*Necessario para implementar o livedata e viewmodel
 * 1- Cria o DAO
 * 2- Cria o BD
 * 3- Cria o repositorio
 * 4- Cria a view*/
public class PostagemViewModel extends AndroidViewModel {
    private PostagemRepository postagemRepository;
    private LiveData<List<Postagem>> listaPostagem;
    private LiveData<Postagem> postagem;

    public PostagemViewModel(@NonNull Application application) {
        super(application);
        postagemRepository = new PostagemRepository(application);
        listaPostagem = postagemRepository.buscarTodas();
    }
    public void inserir (Postagem postagem){
        postagemRepository.inserir(postagem);
    }
    public void atualizar (Postagem postagem){
        postagemRepository.atualizar(postagem);
    }
    public void findById(Long id){
        postagem = postagemRepository.findById(id);
    }
    public void findByNome (String nome){
        listaPostagem = postagemRepository.findByAutor(nome);
    }
    public void findByComponente (Long id){
        listaPostagem = postagemRepository.findByComponente(id);
    }
    public void findByUsuario (Long id){
        listaPostagem = postagemRepository.findByUsuario(id);
    }
    public LiveData<List<Postagem>> getListaPostagem() {
        return listaPostagem;
    }

    public LiveData<Postagem> getPostagem() {
        return postagem;
    }
}
