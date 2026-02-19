package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.CuatroEnRayaExcepcion;
import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola() {
    }

    public static String leerNombre() {

        String nombre;
        do {
            System.out.print("Introduce el nombre del jugador: ");
            nombre = Entrada.cadena();
        } while (nombre.isEmpty());
        return nombre;
    }

    public static Ficha elegirColorFichas() throws CuatroEnRayaExcepcion {
        Ficha colorFichas;
        int opcion;
        System.out.print("Elige el color de tus fichas( 0-AZUL, 1-VERDE): ");
        opcion = Entrada.entero();
        switch (opcion) {
            case 0 -> colorFichas = Ficha.AZUL;
            case 1 -> colorFichas = Ficha.VERDE;
            default -> throw new CuatroEnRayaExcepcion("Opción no válida.");


        }
        return colorFichas;
    }

    public static Jugador leerJugador() throws CuatroEnRayaExcepcion {
        Ficha colorFichas;
        System.out.print("Introduce los datos del PRIMER jugador ");
        String nombre = Entrada.cadena();
        colorFichas = elegirColorFichas();

        return new Jugador(nombre, colorFichas);
    }

    public static Jugador leerJugador(Ficha ficha) throws CuatroEnRayaExcepcion {

        System.out.print("Introduce los datos del SEGUNDO jugador ");
        String nombre = Entrada.cadena();

        return new Jugador(nombre, ficha);
    }

    public static int leerColumna(Jugador jugador) {
        int columna;
        do {
            System.out.print(jugador.nombre() + "introduce la columna en la que deseas introducir la ficha (0 - 6): ");
            columna = Entrada.entero();
        } while (columna < 0 || columna > 6);

        return columna;


    }


}
