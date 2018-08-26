package com.example.angele.imd0033.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.angele.imd0033.dominio.Usuario;

@Database(entities = { Usuario.class},version = 1)
public abstract class UsuarioBD extends RoomDatabase {
    private static UsuarioBD INSTANCE;

    public abstract UsuarioDAO usuarioDAO();

    public static UsuarioBD getInstance (Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,UsuarioBD.class,"usuario").build();
        }
        return INSTANCE;
    }
}
