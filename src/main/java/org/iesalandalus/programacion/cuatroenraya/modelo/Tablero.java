package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Tablero {

    /** Definimos las variables y creamos el array bidimensional **/
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casillas;

    /** 1º Definimos el constructor por defecto **/
    public Tablero() {
        this.casillas = new Casilla[FILAS][COLUMNAS];
    }

    /** 2º Comprobamos que el tablero está vacío **/
    public boolean estaVacio() {
        for (int i = 0; i < COLUMNAS; i++) {
            if (!columnaVacia(i)) {
                return false;
            }
        }
        return true;
    }

    /** 3º Comprobamos que la columna está vacía **/
    private boolean columnaVacia(int columna) {
        return casillas[0][columna] == null;
    }

    /** 4º Comprobamos que el tablero está lleno **/
    public boolean estaLleno() {
        for (int i = 0; i < COLUMNAS; i++) {
            if (!columnaLlena(i)) {
                return false;
            }
        }
        return true;
    }

    /** 5º Comprobamos que una columna está llena **/
    private boolean columnaLlena(int columna) {
        return casillas[0][columna] != null;
    }

    /** 6º Metodo para introducir una ficha **/
    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarColumna(columna);
        comprobarFicha(ficha);

        for (int i = 0; i < FILAS; i++) {
            if (casillas[i][columna] == null || casillas[i][columna].getFicha() == null) {
                casillas[i][columna] = new Casilla(ficha);
                return comprobarTirada(i, columna);
            }
        }

        throw new CuatroEnRayaExcepcion("Columna llena.");
    }

    /** 7º Metodo para comprobar que la ficha no es nula **/
    private void comprobarFicha(Ficha ficha) {
        Objects.requireNonNull(ficha,"La ficha no puede ser nula.");
    }

    /** 8º Comprobamos que la columna no está fuera de rango **/
    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    /** 9º Obtenemos la primera fila vacía de una columna **/
    private int getPrimeraFilaVacia(int columna) {
        for (int i = FILAS - 1; i >= 0; i--) {
            if (casillas[i][columna] == null) {
                return i;
            }
        }
        return -1;
    }

    /** 10º Comprobamos si la tirada es ganadora **/
    private boolean comprobarTirada(int fila, int columna) {
        Ficha ficha = casillas[fila][columna].getFicha();
        return comprobarHorizontal(fila, ficha) ||
                comprobarVertical(columna, ficha) ||
                comprobarDiagonalNE(fila, columna, ficha) ||
                comprobarDiagonalNO(fila, columna, ficha);
    }

    /** 11º Metodo para saber si se alcanzó el objetivo **/
    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    /** 12º Comprobamos horizontalmente **/
    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        for (int i = 0; i < COLUMNAS; i++) {
            if (casillas[fila][i] != null && casillas[fila][i].getFicha().equals(ficha)) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return false;
    }

    /** 13º Comprobamos verticalmente **/
    private boolean comprobarVertical(int columna, Ficha ficha) {
        int contador = 0;
        for (int i = 0; i < FILAS; i++) {
            if (casillas[i][columna] != null && casillas[i][columna].getFicha().equals(ficha)) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return false;
    }

    /** 14º Comprobamos diagonal noreste **/
    public boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha) {
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;
        int contador = 0;

        while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
            if (casillas[filaInicial][columnaInicial] != null && casillas[filaInicial][columnaInicial].getFicha().equals(ficha)) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
            filaInicial++;
            columnaInicial++;
        }
        return false;
    }

    /** 15º Comprobamos diagonal noroeste **/
    public boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;
        int contador = 0;

        while (filaInicial < FILAS && columnaInicial >= 0) {
            if (casillas[filaInicial][columnaInicial] != null && casillas[filaInicial][columnaInicial].getFicha().equals(ficha)) {
                contador++;
                if (objetivoAlcanzado(contador)) {
                    return true;
                }
            } else {
                contador = 0;
            }
            filaInicial++;
            columnaInicial--;
        }
        return false;
    }

    /** 16º Metodo menor **/
    private int menor(int fila, int columna) {
        return Math.min(fila, columna);
    }

    /** 17º Metodo para imprimir el tablero **/
    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        for (int i = FILAS - 1; i >= 0; i--) {
            salida.append("|");
            for (int j = 0; j < COLUMNAS; j++) {
                if (casillas[i][j] == null || casillas[i][j].getFicha() == null) {
                    salida.append(" ");
                } else {
                    Ficha ficha = casillas[i][j].getFicha();
                    if (ficha == Ficha.AZUL) {
                        salida.append("A");
                    } else if (ficha == Ficha.VERDE) {
                        salida.append("V");
                    }
                }
            }
            salida.append("|\n");
        }
        salida.append(" -------\n");
        return salida.toString();
    }
}