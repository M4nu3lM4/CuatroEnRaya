package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Arrays;
import java.util.Objects;

public class Tablero {


    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
    private Casilla[][] casillas = new Casilla[FILAS][COLUMNAS];

    public Tablero() {

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                casillas[fila][columna] = new Casilla();
            }
        }
    }

    public boolean estaVacio() {

       for (int fila = 0; fila < FILAS ; fila++){
           for (int columna = 0; columna < COLUMNAS; columna++){
               if (casillas[fila][columna].estaOcupada()){
                   return false;
               }
           }
       }
       return true;
    }


    private boolean columnaVacia(int columna){
        return !casillas[0][columna].estaOcupada();
    }

    private boolean columnaLlena(int columna){
        return casillas[0][columna].estaOcupada();
    }

    public boolean estaLleno(){
        for (int fila = 0; fila < FILAS ; fila++){
            for (int columna = 0; columna < COLUMNAS; columna++){
                if (!casillas[fila][columna].estaOcupada()){
                    return false;
                }
            }
        }
        return true;
    }


    private void comprobarFicha(Ficha ficha){
        Objects.requireNonNull(ficha,"La ficha no puede ser nula.");

    }

    private void comprobarColumna(int columna){
        if (columna < 0  || columna >= COLUMNAS){
            throw new IllegalArgumentException("Columna incorrecta.");
        }

    }

    private int getPrimeraFilaVacia(int columna) throws CuatroEnRayaExcepcion {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (!casillas[fila][columna].estaOcupada()) {
                return fila;
            }
        }
        return -1;
    }


    private boolean comprobarHorizontal(int fila, Ficha ficha){

        int fichasSeguidas = 0;
        for (int columna = 0; columna < COLUMNAS ; columna++){
            if (casillas[fila][columna].estaOcupada() && casillas[fila][columna].getFicha().equals(ficha)){
                fichasSeguidas++;
                if (objetivoAlcanzado(fichasSeguidas)){
                    return true;
                }
            }else {
                fichasSeguidas = 0;
            }
        }
        return false;
    }

    private boolean comprobarVertical(int columna, Ficha ficha){

        int fichasSeguidas = 0;

        for (int fila = 0; fila < FILAS ; fila++){
            if (casillas[fila][columna].estaOcupada() && casillas[fila][columna].getFicha().equals(ficha)){
                fichasSeguidas++;
                if (objetivoAlcanzado(fichasSeguidas)){
                    return true;
                }
            }else {
                fichasSeguidas = 0;
            }

        }
        return false;

    }

    private int menor(int fila, int columna){
        return Math.min(fila,columna);
    }

    private boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha){
        int desplazamiento = menor(filaActual, columnaActual);

        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;

        int fichasSeguidas = 0;

        for (int fila = filaInicial, columna = columnaInicial;  fila < FILAS && columna < COLUMNAS; fila++, columna++) {

            if (casillas[fila][columna].estaOcupada()
                    && casillas[fila][columna].getFicha().equals(ficha)) {

                fichasSeguidas++;

                if (objetivoAlcanzado(fichasSeguidas)) {
                    return true;
                }

            } else {
                fichasSeguidas = 0;
            }
        }

        return false;
    }

    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha){

        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);

        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;

        int fichasSeguidas = 0;

        for (int fila = filaInicial, columna = columnaInicial; fila < FILAS && columna >= 0;  fila++, columna--) {

            if (casillas[fila][columna].estaOcupada()
                    && casillas[fila][columna].getFicha().equals(ficha)) {

                fichasSeguidas++;

                if (objetivoAlcanzado(fichasSeguidas)) {
                    return true;
                }

            } else {
                fichasSeguidas = 0;
            }
        }
        return false;
    }


    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas){
        return fichasIgualesConsecutivas == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }


    private boolean comprobarTirada(int fila, int columna, Ficha ficha){
        boolean victoria = false;

        if (comprobarHorizontal(fila,ficha)){
            victoria = true;
        }

        if (comprobarVertical(columna,ficha)){
            victoria = true;
        }

        if (comprobarDiagonalNE(fila,columna,ficha)){
            victoria = true;
        }

        if (comprobarDiagonalNO(fila,columna,ficha)){
            victoria = true;
        }
        return victoria;
    }


    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarColumna(columna);
        comprobarFicha(ficha);

        if (columnaLlena(columna)){
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }

        int fila  = getPrimeraFilaVacia(columna);
        casillas[fila][columna].setFicha(ficha);

        return comprobarTirada(fila,columna,ficha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int fila = 0; fila < FILAS; fila++) {
            sb.append("|");
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (casillas[fila][columna].estaOcupada()) {
                    sb.append(casillas[fila][columna].getFicha().name().charAt(0));
                } else {
                    sb.append(" ");
                }
            }
            sb.append("|\n");
        }

        sb.append(" -------\n");

        return sb.toString();
    }


}