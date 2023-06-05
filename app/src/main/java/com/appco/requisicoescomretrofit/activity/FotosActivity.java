package com.appco.requisicoescomretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.appco.requisicoescomretrofit.R;
import com.appco.requisicoescomretrofit.adapter.AdapterFoto;
import com.appco.requisicoescomretrofit.api.DataService;
import com.appco.requisicoescomretrofit.databinding.ActivityFotosBinding;
import com.appco.requisicoescomretrofit.model.Foto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FotosActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private List<Foto> fotosRecuperadas = new ArrayList<>();
    private List<Foto> fotos = new ArrayList<>();
    private ActivityFotosBinding binding;
    private AdapterFoto adapterFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFotosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar.setVisibility(View.VISIBLE);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Configura recycleView
        binding.recycleFotos.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleFotos.setHasFixedSize(true);
        adapterFoto = new AdapterFoto(fotos, this);
        binding.recycleFotos.setAdapter(adapterFoto);

        recuperarListaRetrofit();

    }

    private void recuperarListaRetrofit() {
        DataService service = retrofit.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if (response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);
                    fotosRecuperadas = response.body();

                    for (int i = 0; i < Objects.requireNonNull(fotosRecuperadas).size(); i++) {
                        Foto foto = fotosRecuperadas.get(i);
                        fotos.add(foto);
                    }

                    adapterFoto.notifyDataSetChanged();

                }else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });
    }
}