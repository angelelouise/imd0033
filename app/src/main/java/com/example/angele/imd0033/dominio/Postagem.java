package com.example.angele.imd0033.dominio;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "postagem", foreignKeys = {
        @ForeignKey(entity = Usuario.class,
            parentColumns = {"id","usuario_nome"},
            childColumns = {"id_usuario","usuario"}),
        @ForeignKey(entity = ComponenteCurricular.class,
                parentColumns = "id_componente",
                childColumns = "id_componente_curricular")})

public class Postagem {
    public static final String POSTAGEM = "POSTAGEM_INFO";
    @PrimaryKey(autoGenerate = true)
    private Long id_postagem;

    private Long id_usuario;

    private Long id_componente_curricular;

    private String descricao;

    private String titulo;

    private String usuario;
    public Postagem(Long id_postagem,
                    Long id_usuario,
                    Long id_componente_curricular,
                    String usuario,
                    String descricao,
                    String titulo) {
        this.id_postagem = id_postagem;
        this.id_usuario = id_usuario;
        this.id_componente_curricular = id_componente_curricular;
        this.descricao = descricao;
        this.titulo = titulo;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Postagem{" +
                "id_postagem=" + id_postagem +
                ", id_usuario=" + id_usuario +
                ", id_componente_curricular=" + id_componente_curricular +
                ", descricao='" + descricao + '\'' +
                ", titulo='" + titulo + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getId_postagem() {
        return id_postagem;
    }

    public void setId_postagem(Long id_postagem) {
        this.id_postagem = id_postagem;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_componente_curricular() {
        return id_componente_curricular;
    }

    public void setId_componente_curricular(Long id_componente_curricular) {
        this.id_componente_curricular = id_componente_curricular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
