package com.example.littur;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Frag_Leitura_Ronga extends Fragment {
    private TextView textLivro;
    private TextView textTraducao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Page2 = inflater.inflate(R.layout.leitura_ronga, container, false);

        textLivro = (TextView)Page2.findViewById(R.id.textLivro);
        textTraducao = (TextView)Page2.findViewById(R.id.textPassagemBiblica);

        textLivro.setText(Holder_Leitura.leitura.getLivro());
        textTraducao.setText(Holder_Leitura.leitura.getTraducao());
        return Page2;
    }
}