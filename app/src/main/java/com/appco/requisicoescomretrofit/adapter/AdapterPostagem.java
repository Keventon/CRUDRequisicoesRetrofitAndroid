package com.appco.requisicoescomretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appco.requisicoescomretrofit.R;
import com.appco.requisicoescomretrofit.model.Foto;
import com.appco.requisicoescomretrofit.model.Postagem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPostagem extends RecyclerView.Adapter<AdapterPostagem.MyViewHolder> {

    private List<Postagem> postagens;
    private Context context;

    public AdapterPostagem(List<Postagem> postagens, Context context) {
        this.postagens = postagens;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_postagens, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Postagem postagem = this.postagens.get(i);
        holder.textId.setText("Identificador: " + postagem.getId());
        holder.textUserId.setText("User ID: " + postagem.getUserId());
        holder.textTitulo.setText("Título: " + postagem.getTitle());
        holder.textBody.setText("Título: " + postagem.getBody());


    }

    @Override
    public int getItemCount() {
        return postagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textId, textUserId, textTitulo;
        TextView textBody;

        public MyViewHolder(View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.textIdPostagem);
            textUserId = itemView.findViewById(R.id.textUserId);
            textTitulo = itemView.findViewById(R.id.textTituloPostagem);
            textBody = itemView.findViewById(R.id.textBody);
        }
    }
}
