package com.example.angele.imd0033.Dominio;

public class Discente {
    private int ano_ingresso;
    private String cpf_cnpj;
    private String email;
    private int id_curso;
    private int id_discente;
    private int id_situacao_discente;
    private int matricula;
    private String nome_curso;
    private String nome_discente;
    private int periodo_ingresso;
    private String sigla_nivel;

    public Discente(int ano_ingresso, String cpf_cnpj, String email, int id_curso, int id_discente, int id_situacao_discente, int matricula,
                    String nome_curso, String nome_discente, int periodo_ingresso, String sigla_nivel) {
        this.ano_ingresso = ano_ingresso;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.id_curso = id_curso;
        this.id_discente = id_discente;
        this.id_situacao_discente = id_situacao_discente;
        this.matricula = matricula;
        this.nome_curso = nome_curso;
        this.nome_discente = nome_discente;
        this.periodo_ingresso = periodo_ingresso;
        this.sigla_nivel = sigla_nivel;
    }

    @Override
    public String toString() {
        return "Discente{" +
                "ano_ingresso=" + ano_ingresso +
                ", cpf_cnpj='" + cpf_cnpj + '\'' +
                ", email='" + email + '\'' +
                ", id_curso=" + id_curso +
                ", id_discente=" + id_discente +
                ", id_situacao_discente=" + id_situacao_discente +
                ", matricula=" + matricula +
                ", nome_curso='" + nome_curso + '\'' +
                ", nome_discente='" + nome_discente + '\'' +
                ", periodo_ingresso=" + periodo_ingresso +
                ", sigla_nivel='" + sigla_nivel + '\'' +
                '}';
    }

    public int getAno_ingresso() {
        return ano_ingresso;
    }

    public void setAno_ingresso(int ano_ingresso) {
        this.ano_ingresso = ano_ingresso;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_discente() {
        return id_discente;
    }

    public void setId_discente(int id_discente) {
        this.id_discente = id_discente;
    }

    public int getId_situacao_discente() {
        return id_situacao_discente;
    }

    public void setId_situacao_discente(int id_situacao_discente) {
        this.id_situacao_discente = id_situacao_discente;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public String getNome_discente() {
        return nome_discente;
    }

    public void setNome_discente(String nome_discente) {
        this.nome_discente = nome_discente;
    }

    public int getPeriodo_ingresso() {
        return periodo_ingresso;
    }

    public void setPeriodo_ingresso(int periodo_ingresso) {
        this.periodo_ingresso = periodo_ingresso;
    }

    public String getSigla_nivel() {
        return sigla_nivel;
    }

    public void setSigla_nivel(String sigla_nivel) {
        this.sigla_nivel = sigla_nivel;
    }
}
