package com.example.angele.imd0033.Dominio;

public class Usuario {
    private boolean ativo;
    private String chave_foto;
    private int cpf_cnpj;
    private String email;
    private int id_foto;
    private int id_unidade;
    private int id_usuario;
    private String login;
    private String senha;
    private String nome_pessoa;
    private String url_foto;

    public Usuario(boolean ativo, String chave_foto, int cpf_cnpj, String email, int id_foto, int id_unidade, int id_usuario, String login, String senha, String nome_pessoa, String url_foto) {
        this.ativo = ativo;
        this.chave_foto = chave_foto;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.id_foto = id_foto;
        this.id_unidade = id_unidade;
        this.id_usuario = id_usuario;
        this.login = login;
        this.senha = senha;
        this.nome_pessoa = nome_pessoa;
        this.url_foto = url_foto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ativo=" + ativo +
                ", chave_foto='" + chave_foto + '\'' +
                ", cpf_cnpj=" + cpf_cnpj +
                ", email='" + email + '\'' +
                ", id_foto=" + id_foto +
                ", id_unidade=" + id_unidade +
                ", id_usuario=" + id_usuario +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome_pessoa='" + nome_pessoa + '\'' +
                ", url_foto='" + url_foto + '\'' +
                '}';
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getChave_foto() {
        return chave_foto;
    }

    public void setChave_foto(String chave_foto) {
        this.chave_foto = chave_foto;
    }

    public int getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(int cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public int getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
}
