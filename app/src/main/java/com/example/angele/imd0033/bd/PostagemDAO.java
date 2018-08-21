package com.example.angele.imd0033.bd;

import android.arch.lifecycle.LiveData;

import com.example.angele.imd0033.dominio.Postagem;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
/*Necessario para implementar o livedata e viewmodel
 * 1- Cria o DAO
 * 2- Cria o BD
 * 3- Cria o repositorio
 * 4- Cria a view*/
@Dao
public interface PostagemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserir(Postagem postagem);

    @Update
    public void atualizar(Postagem postagem);

    @Delete
    public void deletar (Postagem postagem);

    @Query("SELECT * FROM postagem WHERE id_postagem = :id LIMIT 1")
    public LiveData<Postagem> findById(Long id);

    @Query("SELECT * FROM postagem WHERE id_usuario = :id_usuario")
    public LiveData<List<Postagem>> findByUsuario(Long id_usuario);

    @Query("SELECT * FROM postagem INNER JOIN usuario ON (usuario.id_usuario = postagem.id_usuario)WHERE usuario.nome_pessoa = :nome")
    public LiveData<List<Postagem>> findByAutor(String nome);

    @Query("SELECT * FROM postagem WHERE id_componente_curricular = :id_componente")
    public LiveData<List<Postagem>> findByComponente(Long id_componente);

    @Query("SELECT * FROM postagem ORDER BY id_postagem ASC")
    public LiveData<List<Postagem>> buscarTodas();
}
