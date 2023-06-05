package com.appco.requisicoescomretrofit.api;

import com.appco.requisicoescomretrofit.model.Foto;
import com.appco.requisicoescomretrofit.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {
    @GET("/photos")
    Call<List<Foto>>recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>>recuperarPostagens();

    //Para salvar em JSON
    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);


    //Para salvar em XML
    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
            @Field("userId") int userId,
            @Field("title") String titulo,
            @Field("body") String body
    );

    //Objeto é atualizado por completo, ou seja, substitui o objeto ntigo pelo novo com o mesmo id
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem);

    //
    @PATCH("/posts/{id}")
    Call<Postagem> atualizarPostagemPatch(@Path("id") int id, @Body Postagem postagem);

    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id") int id);
}
