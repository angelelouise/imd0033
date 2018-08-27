package com.example.angele.imd0033.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.angele.imd0033.dominio.ComponenteCurricular;

@Database(entities = ComponenteCurricular.class, version = 1)
public abstract class CCurricularBD extends RoomDatabase{
    private static CCurricularBD INSTANCE;

    public abstract CCurricularDAO curricularDAO();

    public static CCurricularBD getInstance (Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,CCurricularBD.class,"componente_curricular_bd").build();
        }
        return INSTANCE;
    }
}
