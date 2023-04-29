package com.investimentos.smu.investimentos.domain.utils;

public class GeraNumeros {
    public static String geraNumeroConta(Integer valor) {
        int resto = -1;
        StringBuilder sb = new StringBuilder();
        int len=0;

        sb.insert(0, valor);
        len = sb.length();
        while(len<6){
            sb= new StringBuilder("0" + sb);
            len++;
        }
        return sb.toString();
    }
}
