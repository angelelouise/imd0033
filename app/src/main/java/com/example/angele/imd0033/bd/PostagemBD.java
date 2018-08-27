package com.example.angele.imd0033.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.angele.imd0033.dominio.ComponenteCurricular;
import com.example.angele.imd0033.dominio.Postagem;
import com.example.angele.imd0033.dominio.Usuario;

/*Necessario para implementar o livedata e viewmodel
 * 1- Cria o DAO
 * 2- Cria o BD
 * 3- Cria o repositorio
 * 4- Cria a view*/
@Database(entities = {Postagem.class, Usuario.class, ComponenteCurricular.class},version = 1)
public abstract class PostagemBD extends RoomDatabase{
    private static PostagemBD INSTANCE;

    public abstract PostagemDAO postagemDAO();

    public static PostagemBD getInstance (Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,PostagemBD.class,"postagem_bd").build();
        }
        return INSTANCE;
    }

}
