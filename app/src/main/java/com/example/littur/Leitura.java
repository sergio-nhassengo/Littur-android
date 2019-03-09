package com.example.littur;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Sergio Nhss on 5/3/2018.
 */
public class Leitura implements Serializable{
    private int image;
    private String nrLeitura, livro, texto, traducao;


    public Leitura(){
        livro = "sem livro";
        texto = "sem texto";
        traducao = "sem traducao";
    }
    public Leitura(int image, String nrLeitura, String livro, String texto, String traducao) {
        this.nrLeitura = nrLeitura;
        this.livro = livro;
        this.texto = texto;
        this.traducao = traducao;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNrLeitura() {
        return nrLeitura;
    }

    public void setNrLeitura(String nrLeitura) {
        this.nrLeitura = nrLeitura;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String gettexto() {
        return texto;
    }

    public String getTraducao(){
        return traducao;
    }

    public void settexto(String texto) {
        this.texto = texto;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }
}
