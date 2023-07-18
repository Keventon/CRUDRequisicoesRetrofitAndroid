package com.appco.requisicoescomretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.appco.requisicoescomretrofit.activity.FotosActivity;
import com.appco.requisicoescomretrofit.activity.PostagensActivity;
import com.appco.requisicoescomretrofit.api.DataService;
import com.appco.requisicoescomretrofit.databinding.ActivityMainBinding;
import com.appco.requisicoescomretrofit.model.Foto;
import com.appco.requisicoescomretrofit.model.Postagem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private DataService service;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DataService.class);

        binding.buttonRecuperarFotos.setOnClickListener(v -> startActivity(new Intent(this, FotosActivity.class)));
        binding.buttonRecuperarPostagens.setOnClickListener(v -> startActivity(new Intent(this, PostagensActivity.class)));
        binding.buttonSalvarPostagem.setOnClickListener(v -> salvarPostagem());
        binding.buttonAtualizarPostagem.setOnClickListener(v -> atualizarPostagem());
        binding.buttonRemoverPostagem.setOnClickListener(v -> removerPostagem());
    }

    private void removerPostagem() {
        Call<Void> call = service.removerPostagem(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,
                            "Status: " + response.code()
                            + "\nPostagem removida com sucesso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void atualizarPostagem() {

        Postagem postagem = new Postagem();
        postagem.setBody("Corpo de uma postagem");
        postagem.setTitle(null);
        postagem.setUserId(1234);

        Call<Postagem> call = service.atualizarPostagemPatch(2, postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()) {
                    Postagem postagemResposta = response.body();

                    Toast.makeText(MainActivity.this,
                            "Status: " + response.code()
                            +  "\nID: " + postagemResposta.getId()
                            + "\nTitulo: " + postagemResposta.getTitle() +
                            "\nUser ID: " + postagemResposta.getUserId(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void salvarPostagem() {

        Postagem postagem = new Postagem();
        postagem.setBody("Corpo de uma postagem");
        postagem.setTitle("Titulo da Postagem Teste");
        postagem.setUserId(1234);
        Call<Postagem> call = service.salvarPostagem(postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()) {
                    Postagem postagemResposta = response.body();

                    Toast.makeText(MainActivity.this, "ID: " + postagemResposta.getId() + "\nTitulo: " + postagemResposta.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}