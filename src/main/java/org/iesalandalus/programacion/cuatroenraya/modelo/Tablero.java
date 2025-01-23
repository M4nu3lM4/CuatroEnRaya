package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {

    /**1º Definimos las variables y creamos el array bidimensional**/
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

    private Casilla[][] casillas;

    public Tablero() {

        this.casillas = new Casilla[COLUMNAS][FILAS];

    }

    /**2º Comprobamos que la columna este vacia**/
    public boolean estaVacio() {
        for (int i = 0; i < COLUMNAS; i++) {
            if (!columnaVacia(i)) {
                return false;
            }
        }
        return true;
    }

    /**3º Comprobamos que la última fila de la columna seleccionada esta vacia, si lo está es que toda la columna está vacía**/
    private boolean columnaVacia(int columna) {
        return casillas[columna][FILAS - 1] == null;
    }

    /**4º Comprobamos que la columna este llena**/
    public boolean estaLleno() {
        for (int i = 0; i < COLUMNAS; i++) {
            if (!columnaVacia(i)) {
                return false;
            }
        }
        return true;
    }

    /**5º Comprobamos que la columna esta llena**/
    private boolean columnaLlena(int columna) {
        return casillas[columna][0] == null;
    }

    /**6º Metodo para introducir la ficha**/
    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarColumna(columna);
        comprobarFicha(ficha);
        if (columnaLlena(columna)) {
            throw new IllegalArgumentException(" ERROR: Columna llena.");
        }
        int fila = getPrimeraFilaVacia(columna);
        casillas[columna][fila].setFicha(ficha);
        return comprobarTirada(fila, columna);
    }

    /**7º Metodo para comprobar que la ficha no sea nula**/
    private void comprobarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new NullPointerException("La ficha no puede ser nula.");
        }
    }

    /**8º Comprobamos que la columna no este fuera de rango**/
    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    /**9º Comprobamos que la primera fila sea vaçía**/
    private int getPrimeraFilaVacia(int columna) {
        for (int i = 0; i < FILAS; i++) {
            if (casillas[columna][i] == null) {
                return i;
            }
        }
        return -1;
    }

    private boolean comprobarTirada(int fila, int columna) {
        Ficha ficha = casillas[columna][fila].getFicha();
        return comprobarHorizontal(fila, ficha) ||
                comprobarVertical(columna, ficha) ||
                comprobarDiagonalNE(columna, fila, ficha) ||
                comprobarDiagonalNO(columna, fila,ficha);
    }

    private boolean objetivoAlcanzado(int fichasConsecutivas) {
        return fichasConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }


    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        for (int i = 0; i < COLUMNAS; i++) {
            if (casillas[i][fila] != null && casillas[i][fila].equals(ficha)) {
                contador++;
                if (contador == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return false;
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int contador = 0;
        for (int i = 0; i < COLUMNAS; i++) {
            if (casillas[i][columna] != null && casillas[i][columna].equals(ficha)) {
                contador++;
                if (contador == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }
        return false;
    }

    public boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha) {
        comprobarFicha(ficha);
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;
        int contador = 0;

        while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
            if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
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

    public boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        comprobarFicha(ficha);
        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;
        int contador = 0;

        while (filaInicial < FILAS && columnaInicial >= 0) {
            if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
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

    private int menor(int fila, int columna) {
        return fila < columna ? fila : columna;
    }

    /**
     * Hacemos el metodo para la salida del tablero
     **/
    @Override
    public String toString() {

        StringBuilder salida = new StringBuilder();

        for (int i = FILAS - 1; i >= 0; i--) {
            salida.append("|");
            for (int j = 0; j < COLUMNAS; j++) {
                if (casillas[j][i] == null) {
                    salida.append(" ");
                } else {
                    salida.append(" " + casillas[j][i].getFicha().toString());
                }
                salida.append("|");
            }
            salida.append("\n");
            for (int j = 0; j < COLUMNAS; j++) {
                salida.append(" -");
            }
        }

        return salida.toString();
    }
}
