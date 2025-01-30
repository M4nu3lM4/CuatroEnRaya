package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    // Constructor privado para evitar instanciación
    private Consola() {}

    public static String leerNombre() {
        String nombre;
        do {
            System.out.println("Introduce el nombre del jugador:");
            nombre = Entrada.cadena().trim();
        } while (nombre.isEmpty());
        return nombre;
    }

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

    public static Jugador leerJugador() {
        System.out.println("Introduce los datos del PRIMER jugador");
        String nombre = leerNombre();
        Ficha color = elegirColorFichas();
        return new Jugador(nombre, color);
    }

    public static Jugador leerJugador(Ficha colorFicha) {
        System.out.println("Introduce los datos del SEGUNDO jugador");
        String nombre = leerNombre();
        return new Jugador(nombre, colorFicha);
    }

    public static int leerColumna(Jugador jugador) {
        int columna;
        do {
            System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0 - 6): ", jugador.nombre());
            columna = Entrada.entero();
        } while (columna < 0 || columna > 6);
        return columna;
    }
}
