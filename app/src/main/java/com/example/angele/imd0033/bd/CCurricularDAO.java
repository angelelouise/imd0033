package com.example.angele.imd0033.bd;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.angele.imd0033.dominio.ComponenteCurricular;

import java.util.List;

@Dao
public interface CCurricularDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserir(ComponenteCurricular cc);

    @Update
    public void atualizar(ComponenteCurricular cc);

    @Delete
    public void deletar (ComponenteCurricular cc);

    @Query("SELECT * FROM componente_curricular WHERE id_componente = :id LIMIT 1")
    public ComponenteCurricular findById(int id);

    @Query("SELECT * FROM componente_curricular ORDER BY id_componente_curricular ASC")
    public List<ComponenteCurricular> buscarTodas();
}
