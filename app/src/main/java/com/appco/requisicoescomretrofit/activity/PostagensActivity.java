package com.appco.requisicoescomretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.appco.requisicoescomretrofit.R;
import com.appco.requisicoescomretrofit.adapter.AdapterFoto;
import com.appco.requisicoescomretrofit.adapter.AdapterPostagem;
import com.appco.requisicoescomretrofit.api.DataService;
import com.appco.requisicoescomretrofit.databinding.ActivityFotosBinding;
import com.appco.requisicoescomretrofit.databinding.ActivityPostagensBinding;
import com.appco.requisicoescomretrofit.model.Foto;
import com.appco.requisicoescomretrofit.model.Postagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostagensActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private List<Postagem> postagensRecuperadas = new ArrayList<>();
    private List<Postagem> postagens = new ArrayList<>();
    private ActivityPostagensBinding binding;
    private AdapterPostagem adapterPostagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostagensBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar.setVisibility(View.VISIBLE);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Configura recycleView
        binding.recyclePostagens.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclePostagens.setHasFixedSize(true);
        adapterPostagem = new AdapterPostagem(postagensRecuperadas, this);
        binding.recyclePostagens.setAdapter(adapterPostagem);

        recuperarListaRetrofit();

    }

    private void recuperarListaRetrofit() {
        DataService service = retrofit.create(DataService.class);
        Call<List<Postagem>> call = service.recuperarPostagens();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);
                    //postagensRecuperadas = response.body();
                    postagensRecuperadas.addAll(response.body());

                    adapterPostagem.notifyDataSetChanged();

                }else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {
                Toast.makeText(PostagensActivity.this, "Erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}