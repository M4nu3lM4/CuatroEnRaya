package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Casilla {
    private Ficha ficha;


    public Casilla()  {
        ficha = null;

    }

    public Ficha getFicha() {

        return ficha;
    }

    public void setFicha(Ficha ficha) throws CuatroEnRayaExcepcion {

        Objects.requireNonNull(ficha,"No se puede poner una ficha nula.");

        if (this.ficha != null){
            throw new CuatroEnRayaExcepcion("La casilla ya contiene una ficha.");
        }
        this.ficha = ficha;


    }

    public boolean estaOcupada(){
        if (ficha == null){
            return false;
        }else {
            return true;

        }
    }

    @Override
    public String toString() {

        return (ficha != null) ? ficha.name().substring(0, 1) : String.format(" ");

    }
}
