package com.example.angele.imd0033.api;

import com.example.angele.imd0033.Dominio.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UsuarioService {
    @GET("/usuario")
    @Headers({
            "Accept: application/json"
    })
    Call<List<Usuario>> UsuarioDTO();

    @GET("usuario/{login}")
    @Headers({
            "Accept: application/json"
    })
    Call<Usuario> UsuarioDTO(@Path("login") String login);
}
