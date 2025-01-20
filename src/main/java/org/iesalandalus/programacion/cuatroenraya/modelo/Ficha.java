package org.iesalandalus.programacion.cuatroenraya.modelo;

public enum Ficha {
    AZUL("A") , VERDE("V");

    private String cadenaAmostrar;

    private Ficha(String cadenaAmostrar){

        this.cadenaAmostrar = cadenaAmostrar;
    }

    @Override
    public String toString() {
        return cadenaAmostrar;
    }
}

