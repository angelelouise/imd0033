package com.example.angele.imd0033.bd;

import android.arch.lifecycle.LiveData;

import com.example.angele.imd0033.Dominio.Postagem;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface PostagemDAO {
    @Insert
    public void inserirPostagem(Postagem postagem);

    @Query("SELECT * FROM postagem ORDER BY id_postagem ASC")
    public LiveData<List<Postagem>> buscarTodas();
}
