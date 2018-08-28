package com.example.angele.imd0033.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.angele.imd0033.bd.UsuarioRepository;
import com.example.angele.imd0033.dominio.Usuario;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    private List<Usuario> usuarios;

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }

    public Usuario findByLogin(String login){
        usuario = usuarioRepository.findByLogin(login);
        return usuario;
    }
    public List <Usuario>buscarTodos(){
        usuarios = usuarioRepository.buscarTodos();
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
