package com.appco.requisicoescomretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appco.requisicoescomretrofit.R;
import com.appco.requisicoescomretrofit.model.Foto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFoto extends RecyclerView.Adapter<AdapterFoto.MyViewHolder> {

    private List<Foto> fotos;
    private Context context;

    public AdapterFoto(List<Foto> fotos, Context context) {
        this.fotos = fotos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fotos, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Foto foto = this.fotos.get(i);
        holder.textId.setText("Identificador: " + foto.getId());
        holder.textAlbumId.setText("Álbum: " + foto.getAlbumId());
        holder.textTitulo.setText("Título: " + foto.getTitle());

        Picasso.get().load(foto.getUrl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textId, textAlbumId, textTitulo;
        ImageView image;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.textId);
            textAlbumId = itemView.findViewById(R.id.textAlbumId);
            textTitulo = itemView.findViewById(R.id.textTitulo);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardFotos);
        }
    }
}
