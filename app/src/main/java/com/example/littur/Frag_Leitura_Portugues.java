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


public class Frag_Leitura_Portugues extends Fragment {
    private TextView textLivro;
    private TextView textPassBiblica;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View Page1 = inflater.inflate(R.layout.leitura_pt, container, false);

        textLivro = (TextView)Page1.findViewById(R.id.textLivro);
        textPassBiblica = (TextView)Page1.findViewById(R.id.textPassagemBiblica);

        textLivro.setText(Holder_Leitura.leitura.getLivro());
        textPassBiblica.setText(Holder_Leitura.leitura.gettexto());
        return Page1;
    }
}
