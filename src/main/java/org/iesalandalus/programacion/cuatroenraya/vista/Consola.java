package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {


    private Consola() {}

/**1º leerá el nombre del jugador mientras este sea válido.**/
    public static String leerNombre() {
        String nombre;
        do {
            System.out.println("Introduce el nombre del jugador:");
            nombre = Entrada.cadena().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

/**2º leerá el color de una ficha mientras este no sea válido. **/
    public static Ficha elegirColorFichas() {
        Ficha colorFichas = null;
        do {
            System.out.println("Elige el color de tus fichas (0-AZUL, 1-VERDE):");
            int eleccion = Entrada.entero();
            if (eleccion == 0) {
                colorFichas = Ficha.AZUL;
            } else if (eleccion == 1) {
                colorFichas = Ficha.VERDE;
            }
        } while (colorFichas == null);
        return colorFichas;
    }

/**3º leerá el nombre del primer jugador y el color de sus fichas y lo devolverá. **/
    public static Jugador leerJugador() {
        System.out.println("Introduce los datos del PRIMER jugador");
        String nombre = leerNombre();
        Ficha color = elegirColorFichas();
        return new Jugador(nombre, color);
    }

/**4º leerá el nombre del segundo jugador y devolverá dicho jugador con el color de ficha pasado por parámetro. **/
    public static Jugador leerJugador(Ficha colorFicha) {
        System.out.println("Introduce los datos del SEGUNDO jugador");
        String nombre = leerNombre();
        return new Jugador(nombre, colorFicha);
    }

/**5º imprimirá el nombre del jugador y le indicará que elija la columna en la que quiere introducir su ficha y esto lo repetirá mientras la columna elegida no sea válida. **/
    public static int leerColumna(Jugador jugador) {
        int columna;
        do {
            System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0 - 6): ", jugador.nombre());
            columna = Entrada.entero();
        } while (columna < 0 || columna > 6);
        return columna;
    }
}
