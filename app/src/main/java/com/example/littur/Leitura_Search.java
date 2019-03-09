package com.example.littur;

import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sergio Nhss on 7/13/2018.
 * Esta classe cria parametros para fazer a busca da leitura na base de dados a partir do dia seleccionado
 * que e passado como parametro
 */
public class Leitura_Search {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

    public static List<Leitura> searchLeitura(Context context, int year, int month, int day) throws ParseException {
       List<Leitura>leituraList = new ArrayList<>();
        String query, livro=null, texto=null, traducao=null; Cursor c;
        GregorianCalendar data = new GregorianCalendar(year, month, day);

        /*char anoLiturgico = calculaAno(data);
        String tempo = calculaTempoLiturgico(data);
        byte diaLiturgico = calculaDiaLiturgico(data, tempo);*/

        livro = "livro 1";
        texto = "texto 1 texto 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 ";
        traducao = "traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 ";
        leituraList.add( new Leitura(R.drawable.let1, "Primeira Leitura", livro, texto, traducao));

        livro = "livro 2";
        texto = "texto 2 texto 2 texto 2 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 2 texto 2 ";
        traducao = "traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 ";
        leituraList.add( new Leitura(R.drawable.let1, "Segunda Leitura", livro, texto, traducao));

        livro = "livro 3";
        texto = "texto 3 texto 3 texto 3 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 3 ";
        traducao = "traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 ";
        leituraList.add( new Leitura(R.drawable.let1, "salmos", livro, texto, traducao));

        livro = "livro 4";
        texto = "texto 4 texto 4 texto 4 texto 4 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 1 1 texto 2 texto 2 ";
        traducao = "traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 traducao 1 ";
        leituraList.add( new Leitura(R.drawable.let1, "Evangelho", livro, texto, traducao));
        return leituraList;
    }

    public static char calculaAno(GregorianCalendar day){
        GregorianCalendar inicioLit = inicioLiturgico(day.get(Calendar.YEAR));
        GregorianCalendar fimLit = fimLiturgico(day.get(Calendar.YEAR));
        Date inicio = inicioLit.getTime();
        Date fim = fimLit.getTime();
        Date data = day.getTime();
        int ano = 0;

        if(data.compareTo(inicio) >= 0){
            if(data.compareTo(fim) <= 0)
                ano = fimLit.get(Calendar.YEAR);
            else
                ano = fimLit.get(Calendar.YEAR) + 1;
        }else{
            ano = inicioLit.get(Calendar.YEAR);
        }

        int soma = 0;
        while(ano>0){
            soma+=(ano%10);
            ano/=10;
        }

        char resp = 0;
        if(soma%3==0)
            resp = 'C';
        if((soma-1)%3==0)
            resp = 'A';
        if((soma-2)%3==0)
            resp = 'B';
        return resp;
    } //checked

    public static GregorianCalendar inicioLiturgico(int ano){
        GregorianCalendar dataMin = new GregorianCalendar(ano, 10, 27);
        GregorianCalendar dataMax = new GregorianCalendar(ano, 11, 3);
        GregorianCalendar inicio = new GregorianCalendar();

        while(!dataMin.after(dataMax)){
            if(dataMin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                inicio.setTime(dataMin.getTime());
            }
            dataMin.add(Calendar.DATE, 1);
        }

        return inicio;
    } //checked

    public static GregorianCalendar fimLiturgico(int ano) {
        GregorianCalendar fim = inicioLiturgico(ano);
        fim.add(Calendar.DATE, -7);

        return fim;
    } //checked

    public static String calculaTempoLiturgico(GregorianCalendar day) throws ParseException{

        int ano = day.get(Calendar.YEAR);
        GregorianCalendar inicioTempo, fimTempo;
        Date inicio, fim, data = day.getTime();

        //Procura no Advento
        inicioTempo = inicioLiturgico(ano);
        inicio = inicioTempo.getTime();
        fim = sdf.parse("24/12/"+ano);

        if(data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0){
            System.err.println("Advento");
            return "Advento";
        }


        //Procura no Natal
        Date inicioNatalPassado = sdf.parse("25/12/"+(ano-1));
        Date inicioNatalPresente = sdf.parse("25/12/"+ano);
        GregorianCalendar fimNatal1 = findBaptismoSenhor(ano);
        GregorianCalendar fimNatal2 = findBaptismoSenhor(ano+1);
        Date fimNatalPassado = fimNatal1.getTime();
        Date fimNatalPresente = fimNatal2.getTime();

        if((data.compareTo(inicioNatalPassado) >= 0 && data.compareTo(fimNatalPassado) <= 0) || (data.compareTo(inicioNatalPresente) >= 0 && data.compareTo(fimNatalPresente) <= 0)){
            System.err.println("Natal");
            return "Natal";
        }


        //Procura no Tempo Comum 1
        inicioTempo = findBaptismoSenhor(ano);
        inicioTempo.add(Calendar.DATE, 1);
        Date inicio1 = inicioTempo.getTime();
        fimTempo  = findPascoa(ano);
        fimTempo.add(Calendar.DATE, -47);
        Date fim1 = fimTempo.getTime();

        inicioTempo = findPascoa(ano);
        inicioTempo.add(Calendar.DATE, 49);
        Date inicio2 = inicioTempo.getTime();
        fimTempo  = inicioLiturgico(ano);
        fimTempo.add(Calendar.DATE, -1); //Sabado antes do Advento
        Date fim2 = fimTempo.getTime();

        if((data.compareTo(inicio1) >= 0 && data.compareTo(fim1) <= 0) || (data.compareTo(inicio2) >= 0 && data.compareTo(fim2) <= 0)){
            System.err.println("Tempo Comum");
            return "Tempo Comum";
        }


        //Procura na Quaresma
        inicioTempo = findPascoa(ano);
        fimTempo  = findPascoa(ano);
        inicioTempo.add(Calendar.DATE, -47);
        fimTempo.add(Calendar.DATE, -8);
        inicio = inicioTempo.getTime();
        fim = fimTempo.getTime();
        if(data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0){
            System.err.println("Quaresma");
            return "Quaresma";
        }

        //Procura na Semana Santa
        inicioTempo = findPascoa(ano);
        fimTempo  = findPascoa(ano);
        fimTempo.add(Calendar.DATE, -1);
        inicioTempo.add(Calendar.DATE, -8);
        inicio = inicioTempo.getTime();
        fim = fimTempo.getTime();

        if(data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0){
            System.err.println("Semana Santa");
            return "Semana Santa";
        }

        //Procura na Pascoa
        inicioTempo = findPascoa(ano);
        inicioTempo.add(Calendar.DATE, -1);
        fimTempo  = findPascoa(ano);
        fimTempo.add(Calendar.DATE, 49);
        inicio = inicioTempo.getTime();
        fim = fimTempo.getTime();

        if(data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0){
            System.err.println("Pascoa");
            return "Pascoa";
        }

        return null;
    }

    public static byte procuraDia(GregorianCalendar inicio, GregorianCalendar fim, GregorianCalendar data){
        byte dia = 0;

        while(!inicio.after(fim)){
            if(inicio.get(Calendar.DAY_OF_WEEK) == data.get(Calendar.DAY_OF_WEEK)){
                if((data.get(Calendar.DATE) == inicio.get(Calendar.DATE)) && (data.get(Calendar.MONTH) == inicio.get(Calendar.MONTH)) && (data.get(Calendar.YEAR) == inicio.get(Calendar.YEAR))){
                    dia++;
                    return dia;

                }else dia++;

            }
            inicio.add(Calendar.DATE, 1);
        }

        return dia;
    }

    public static byte calculaDiaLiturgico(GregorianCalendar data, String tempo) throws ParseException{
        int ano = data.get(Calendar.YEAR);
        GregorianCalendar inicio = null, fim = null;

        switch(tempo){
            case "Advento": //Procura no Advento
                inicio = inicioLiturgico(ano);
                fim = new GregorianCalendar(ano, 11, 24); break;

            case "Natal": //Procura no Natal
                GregorianCalendar inicioPassado = new GregorianCalendar(ano-1, 11, 25);
                GregorianCalendar fimPassado = findBaptismoSenhor(ano);

                if(data.compareTo(inicioPassado) >= 0 && data.compareTo(fimPassado) <= 0){
                    inicio = inicioPassado; //inicio da pascoa do ano passado
                    fim = fimPassado; //fim da pascoa do ano passado
                }else{
                    inicio = new GregorianCalendar(ano, 11, 25); // inicio da pascoa do presente ano
                    fim = findBaptismoSenhor(ano+1); // fim da pascoa do presente ano
                }   break;

            case"Quaresma": //Procura na Quaresma
                inicio = findPascoa(ano);
                inicio.add(Calendar.DATE, -46);
                fim = findPascoa(ano);
                fim.add(Calendar.DATE, -8); break;

            case"Semana Santa": //Procura na Semana Santa
                inicio = findPascoa(ano);
                fim = findPascoa(ano);
                inicio.add(Calendar.DATE, -6); break;

            case"Pascoa": //Procura na Pascao
                inicio = findPascoa(ano);
                fim  = findPascoa(ano);
                fim.add(Calendar.DATE, 49); break;

            default: //Caso do tempo comum
                break;
        }

        if(tempo.equals("Tempo Comum"))
            return procuraDiaComum(ano, data);
        else
            return procuraDia(inicio, fim, data);
    }

    public static byte procuraDiaComum(int ano, GregorianCalendar data){
        System.err.println("dia comum");
        byte dia = 35;
        GregorianCalendar inicio; GregorianCalendar fim;

        //Procura no Tempo Comum 1
        inicio = findBaptismoSenhor(ano);
        inicio.add(Calendar.DATE, 1);
        fim  = findPascoa(ano);
        fim.add(Calendar.DATE, -47);
        Date start = inicio.getTime(), end = fim.getTime(), day = data.getTime();

        if(day.compareTo(start) >= 0 && day.compareTo(end) <= 0)
            return procuraDia(inicio, fim, data);
        else{
            //Procura no Tempo Comum 2
            inicio = findPascoa(ano);
            fim = inicioLiturgico(ano);
            inicio.add(Calendar.DATE, 49);
            fim.add(Calendar.DATE, -1);
            System.out.println("Start "+sdf.format(inicio.getTime()));
            System.out.println("End "+sdf.format(fim.getTime()));
            System.out.println("Data "+sdf.format(data.getTime()));

            while(!inicio.after(fim)){
                if(fim.get(Calendar.DAY_OF_WEEK) == data.get(Calendar.DAY_OF_WEEK)){
                    if((data.get(Calendar.DATE) == fim.get(Calendar.DATE)) && (data.get(Calendar.MONTH) == fim.get(Calendar.MONTH)) && (data.get(Calendar.YEAR) == fim.get(Calendar.YEAR))){
                        dia--;
                        System.err.println("Decrementando "+dia);
                        return dia;
                    }else{
                        dia--;
                        System.err.println("Decrementando "+dia);
                    }
                }
                fim.add(Calendar.DATE, -1);
            }

            return dia;
        }
    }

    public static GregorianCalendar findEpifania(int ano){
        GregorianCalendar diaEpifania = new GregorianCalendar(ano, 0, 2);
        GregorianCalendar dia8 = new GregorianCalendar(ano, 0, 8);
        while(!diaEpifania.after(dia8)){
            if(diaEpifania.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                diaEpifania.setTime(diaEpifania.getTime());
                break;
            }
            diaEpifania.add(Calendar.DATE, 1);
        }
        return diaEpifania;
    } //checked

    public static GregorianCalendar findBaptismoSenhor(int ano){
        GregorianCalendar diaBaptismo = findEpifania(ano);
        GregorianCalendar dia9 = new GregorianCalendar(ano, 0, 9);
        GregorianCalendar dia13 = new GregorianCalendar(ano, 0, 13);

        while(dia13.after(dia9)){
            if(dia9.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                break;
            }
            dia9.add(Calendar.DATE, 1);
        }
        if(dia9.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            diaBaptismo.setTime(dia9.getTime());
        else{
            diaBaptismo.add(Calendar.DATE, 1);
        }

        return diaBaptismo;
    } //checked

    public static GregorianCalendar findPascoa(int ano){
        GregorianCalendar data_Pascoa = new GregorianCalendar();

        int a = ano % 19;
        int b = ano / 100;
        int c = ano % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31;
        int dia = ((h + l -7 * m + 114) % 31) + 1;

        data_Pascoa.set(Calendar.YEAR, ano);
        data_Pascoa.set(Calendar.MONTH, mes - 1);
        data_Pascoa.set(Calendar.DAY_OF_MONTH, dia);

        return data_Pascoa;
    } //checked
}
