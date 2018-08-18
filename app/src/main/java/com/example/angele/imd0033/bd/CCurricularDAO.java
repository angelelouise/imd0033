package com.example.angele.imd0033.bd;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.angele.imd0033.Dominio.ComponenteCurricular;

import java.util.List;

public interface CCurricularDAO {
    @Insert
    public void inserirCC(ComponenteCurricular cc);

    @Query("SELECT * FROM componente_curricular ORDER BY id_componente_curricular ASC")
    public List<ComponenteCurricular> buscarTodas();
}
