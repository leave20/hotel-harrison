package com.hotelharrison.api.utils.other;

import java.util.concurrent.ThreadLocalRandom;

public class CodigoReserva {

    public String generarCodigo(){
        int longitud = 10;
        String cadena = "";
        String banco = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }

        return cadena;
    }

    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

}
