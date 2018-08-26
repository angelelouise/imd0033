package com.example.angele.imd0033.bd;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.example.angele.imd0033.bd.firestore.UsuarioDAOFirestore;
import com.example.angele.imd0033.dominio.Usuario;

import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios;
    private Usuario usuario;

    private UsuarioDAO usuarioDAO;
    private UsuarioDAOFirestore usuarioDAOFirestore;

    private ConnectivityManager cm;

    public UsuarioRepository(Application app) {
        usuarioDAO = UsuarioBD.getInstance(app).usuarioDAO();
        usuarioDAOFirestore = new UsuarioDAOFirestore();
        cm =(ConnectivityManager)app
                .getSystemService(app.CONNECTIVITY_SERVICE);
    }
    public Usuario findByLogin (String login){
        if (netOn()){
            if (usuario==null){
                usuario = usuarioDAOFirestore.findByLogin(login);
            }
        }else{
            if (usuario==null){
                usuario = usuarioDAO.findByLogin(login);
            }
        }
        return usuario;
    }

    public List<Usuario> buscarTodos (){
        if (netOn()){
            if (usuarios==null){
                usuarios = usuarioDAOFirestore.buscarTodos();
            }
        }else{
            if (usuario==null){
                usuarios = usuarioDAO.buscarTodos();
            }
        }
        return usuarios;
    }

    private class InsertASync extends AsyncTask<Usuario, Void, Void> {
        private UsuarioDAO usuarioDAO;

        public InsertASync (UsuarioDAO usuarioDAO){
            this.usuarioDAO= usuarioDAO;
        }
        @Override
        protected Void doInBackground(Usuario... usuarios){
            Usuario p = usuarios[0];
            if(p.getId_usuario() == 0)
                usuarioDAO.inserir(p);
            else
                usuarioDAO.atualizar(p);
            return null;
        }
    }
    private boolean netOn(){
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
