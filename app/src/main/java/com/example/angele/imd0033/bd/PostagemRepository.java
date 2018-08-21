package com.example.angele.imd0033.bd;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.example.angele.imd0033.dominio.Postagem;

import java.util.List;

/*Necessario para implementar o livedata e viewmodel
* 1- Cria o DAO
* 2- Cria o BD
* 3- Cria o repositorio
* 4- Cria a view*/
public class PostagemRepository {
    private LiveData<List<Postagem>> listaPostagem;
    private LiveData<Postagem> postagem;
    private PostagemDAO postagemDAO;

    public PostagemRepository(Application app) {
        postagemDAO = PostagemBD.getInstance(app).postagemDAO();
    }

    public LiveData<List<Postagem>> buscarTodas() {
        if (listaPostagem==null){
            listaPostagem = postagemDAO.buscarTodas();
        }
        return listaPostagem;
    }

    public LiveData<Postagem> findById(Long id) {
        if (postagem==null){
            postagem = postagemDAO.findById(id);
        }
        return postagem;
    }
    public LiveData<List<Postagem>> findByUsuario(Long id) {
        if (listaPostagem==null){
            listaPostagem = postagemDAO.findByUsuario(id);
        }
        return listaPostagem;
    }
    public LiveData<List<Postagem>> findByComponente(Long id) {
        if (listaPostagem==null){
            listaPostagem = postagemDAO.findByComponente(id);
        }
        return listaPostagem;
    }
    public LiveData<List<Postagem>> findByAutor(String nome) {
        if (listaPostagem==null){
            listaPostagem = postagemDAO.findByAutor(nome);
        }
        return listaPostagem;
    }

    public void inserir (Postagem postagem){
        new InsertASync(postagemDAO).execute(postagem);
    }

    public void atualizar (Postagem postagem){
        new InsertASync(postagemDAO).execute(postagem);
    }

    private class InsertASync extends AsyncTask<Postagem, Void, Void> {
        private PostagemDAO postagemDAO;

        public InsertASync (PostagemDAO postagemDAO){
            this.postagemDAO= postagemDAO;
        }
        @Override
        protected Void doInBackground(Postagem... postagens){
            Postagem p = postagens[0];
            if(p.getId_postagem() == 0)
                postagemDAO.inserir(p);
            else
                postagemDAO.atualizar(p);
            return null;
        }
    }

}
