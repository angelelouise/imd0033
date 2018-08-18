package com.example.angele.imd0033.bd;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.angele.imd0033.Dominio.Usuario;

import java.util.List;

public interface UsuarioDAO {
    @Insert
    public void inserirUsuario(Usuario usuario);

    @Query("SELECT * FROM usuario ORDER BY id_usuario ASC")
    public List<Usuario> buscarTodos();
}
