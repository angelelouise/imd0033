package com.example.angele.imd0033.bd;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.angele.imd0033.dominio.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserir(Usuario usuario);

    @Update
    public void atualizar(Usuario usuario);

    @Delete
    public void deletar (Usuario usuario);

    @Query("SELECT * FROM usuario ORDER BY id ASC")
    public List<Usuario> buscarTodos();

    @Query("SELECT * FROM usuario WHERE login = :login LIMIT 1")
    public Usuario findByLogin(String login);
}
