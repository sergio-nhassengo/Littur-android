package com.example.littur;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sergio Nhss on 5/3/2018.
 */
public class Adapter_Leitura extends RecyclerView.Adapter<Adapter_Leitura.LeituraViewHolder>{

    private Context mCtx;
    private List<Leitura> leituraList;
    Leitura leitura;

    public Adapter_Leitura(Context mCtx, List<Leitura> leituraList) {
        this.mCtx = mCtx;
        this.leituraList = leituraList;
    }

    @Override
    public LeituraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mCtx).inflate(R.layout.list_content_home, parent, false);

        return new LeituraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeituraViewHolder holder, int position) {
        leitura = leituraList.get(position);

        holder.textViewNrLeitura.setText(leitura.getNrLeitura());
        holder.textViewLivro.setText(leitura.getLivro());
        holder.imageView.setImageResource(leitura.getImage());

        holder.leitura = leitura;
    }

    @Override
    public int getItemCount() {
        return leituraList.size();
    }

    class LeituraViewHolder extends RecyclerView.ViewHolder{
        public View view;
        TextView textViewNrLeitura, textViewLivro;
        ImageView imageView;
        public Leitura leitura;

        public LeituraViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, Activity_Leitura.class);
                    intent.putExtra("key", leitura);
                    mCtx.startActivity(intent);
                }
            });

            textViewNrLeitura = (TextView) itemView.findViewById(R.id.textViewNrLeitura);
            textViewLivro = (TextView) itemView.findViewById(R.id.textViewLivro);
            imageView = (ImageView) itemView.findViewById((R.id.imageView));
        }
    }
}
