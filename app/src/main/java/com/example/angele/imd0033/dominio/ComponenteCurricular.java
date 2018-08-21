package com.example.angele.imd0033.dominio;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "componente_curricular")
public class ComponenteCurricular {

    private int carga_horaria_total;
    private String co_requisitos;
    private String codigo;
    private String componentesBloco;
    private String departamento;
    private String disciplina_obrigatoria;
    private String equivalentes;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_componente")
    private int id_componente;

    private int id_matriz_curricular;
    private String nome;
    private String pre_requisitos;
    private int semestre_oferta;

    public ComponenteCurricular(int carga_horaria_total,
                                String co_requisitos,
                                String codigo,
                                String componentesBloco,
                                String departamento,
                                String disciplina_obrigatoria,
                                String equivalentes,
                                int id_componente,
                                int id_matriz_curricular,
                                String nome,
                                String pre_requisitos,
                                int semestre_oferta) {

        this.carga_horaria_total = carga_horaria_total;
        this.co_requisitos = co_requisitos;
        this.codigo = codigo;
        this.componentesBloco = componentesBloco;
        this.departamento = departamento;
        this.disciplina_obrigatoria = disciplina_obrigatoria;
        this.equivalentes = equivalentes;
        this.id_componente = id_componente;
        this.id_matriz_curricular = id_matriz_curricular;
        this.nome = nome;
        this.pre_requisitos = pre_requisitos;
        this.semestre_oferta = semestre_oferta;
    }

    @Override
    public String toString() {
        return "ComponenteCurricular{" +
                "carga_horaria_total=" + carga_horaria_total +
                ", co_requisitos='" + co_requisitos + '\'' +
                ", codigo='" + codigo + '\'' +
                ", componentesBloco='" + componentesBloco + '\'' +
                ", departamento='" + departamento + '\'' +
                ", disciplina_obrigatoria='" + disciplina_obrigatoria + '\'' +
                ", equivalentes='" + equivalentes + '\'' +
                ", id_componente=" + id_componente +
                ", id_matriz_curricular=" + id_matriz_curricular +
                ", nome='" + nome + '\'' +
                ", pre_requisitos='" + pre_requisitos + '\'' +
                ", semestre_oferta=" + semestre_oferta +
                '}';
    }

    public int getCarga_horaria_total() {
        return carga_horaria_total;
    }

    public void setCarga_horaria_total(int carga_horaria_total) {
        this.carga_horaria_total = carga_horaria_total;
    }

    public String getCo_requisitos() {
        return co_requisitos;
    }

    public void setCo_requisitos(String co_requisitos) {
        this.co_requisitos = co_requisitos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getComponentesBloco() {
        return componentesBloco;
    }

    public void setComponentesBloco(String componentesBloco) {
        this.componentesBloco = componentesBloco;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDisciplina_obrigatoria() {
        return disciplina_obrigatoria;
    }

    public void setDisciplina_obrigatoria(String disciplina_obrigatoria) {
        this.disciplina_obrigatoria = disciplina_obrigatoria;
    }

    public String getEquivalentes() {
        return equivalentes;
    }

    public void setEquivalentes(String equivalentes) {
        this.equivalentes = equivalentes;
    }

    public int getId_componente() {
        return id_componente;
    }

    public void setId_componente(int id_componente) {
        this.id_componente = id_componente;
    }

    public int getId_matriz_curricular() {
        return id_matriz_curricular;
    }

    public void setId_matriz_curricular(int id_matriz_curricular) {
        this.id_matriz_curricular = id_matriz_curricular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPre_requisitos() {
        return pre_requisitos;
    }

    public void setPre_requisitos(String pre_requisitos) {
        this.pre_requisitos = pre_requisitos;
    }

    public int getSemestre_oferta() {
        return semestre_oferta;
    }

    public void setSemestre_oferta(int semestre_oferta) {
        this.semestre_oferta = semestre_oferta;
    }
}
