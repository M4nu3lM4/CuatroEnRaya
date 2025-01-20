package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Casilla {

    private Ficha ficha;

    public Casilla(){
        ficha = null;

    }
    public Ficha getFicha(){
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        if (ficha == null){
            Objects.requireNonNull(ficha,"La ficha no puede ser nula");
        }
        this.ficha = ficha;
    }

    public boolean estaOcupada(){
        return ficha!=null;

    }

    @Override
    public String toString() {
        return String.format("[ficha=%s]", ficha);
    }
}
