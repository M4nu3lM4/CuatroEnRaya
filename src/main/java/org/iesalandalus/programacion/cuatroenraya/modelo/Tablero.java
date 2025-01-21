package org.iesalandalus.programacion.cuatroenraya.modelo;
import  org.iesalandalus.programacion.cuatroenraya.modelo.*;
public class Tablero {

    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casillas;

    public Tablero(){

        this.casillas = new Casilla[COLUMNAS][FILAS];

    }
    /**1º Comprobamos que la columna este vacia**/
    public boolean estaVacio(){
        for (int i = 0; i < COLUMNAS; i++){
            if (!columnaVacia(i)){
                return false;
            }
        }
        return true;
    }

    public boolean estaLleno(){
        for (int i = 0; i < COLUMNAS; i++){
            if (!columnaVacia(i)){
                return false;
            }
        }
        return true;
    }

    public boolean introducirFicha(int columna, Ficha ficha){
        comprobarColumna(columna);
        if(columnaLlena(columna)){
            throw new IllegalArgumentException("ERROR: Columna llena.");
        }
        int fila = getPrimeraFilaVacia(columna);
        casillas[columna][fila].setFicha(ficha);
        return comprobarTirada(fila,columna,ficha);
    }

    /** 2º Comprobamos que la última fila de la columna seleccionada esta vacia, si lo está es que toda la columna está vacía**/
    private boolean columnaVacia(int columna){
        return casillas[columna][FILAS - 1] == null;
    }
    /**3º Comprobamos que la columna esta llena**/
    private boolean columnaLlena(int columna){
        return casillas[columna][0] == null;
    }

    private void comprobarFila(int fila){
        if (fila < 0 || fila >= FILAS){
            throw new IllegalArgumentException("ERROR: La fila esta fuera de rango.");
        }
    }

    private void comprobarColumna(int columna){
        if (columna < 0 || columna >= COLUMNAS){
            throw new IllegalArgumentException("ERROR: La columna esta fuera de rango.");
        }
    }

    private int getPrimeraFilaVacia(int columna){
        for(int i = 0; i < FILAS; i++){
            if (casillas[columna][i] == null){
                return i;
            }
        }
        return  -1;
    }


    private  boolean comprobarHorizontal(int fila, Ficha ficha){
    int contador = 0;
    for(int i = 0; i < COLUMNAS; i++){
        if(casillas[i][fila] != null && casillas[i][fila].equals(ficha)) {
            contador++;
            if (contador == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS ){
                return true;
            }
        }else {
            contador = 0;
        }
    }
    return false;
    }

    private  boolean comprobarVertical(int columna, Ficha ficha){
        int contador = 0;
        for(int i = 0; i < COLUMNAS; i++){
            if(casillas[i][columna] != null && casillas[i][columna].equals(ficha)) {
                contador++;
                if (contador == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS ){
                    return true;
                }
            }else {
                contador = 0;
            }
        }
        return false;
    }

    private boolean comprobarDiagonalINE(int columna, int fila, Ficha ficha){
        return false;
    }

    private boolean comprobarDiagonalINO(int columna, int fila, Ficha ficha){
        return false;
    }


    private  int menor (int fila, int columna){
        return fila < columna ? fila : columna;
    }


    private boolean comprobarTirada(int fila , int columna, Ficha ficha){

    }






















@Override
    public String toString(){
        StringBuilder salida = new StringBuilder();

        for(int i = FILAS -1; i >= 0; i--){
            salida.append("|");

        }
}

}
