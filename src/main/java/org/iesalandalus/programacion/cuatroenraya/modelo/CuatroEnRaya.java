package org.iesalandalus.programacion.cuatroenraya.modelo;


import org.iesalandalus.programacion.cuatroenraya.vista.Consola;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import java.util.Objects;

public class CuatroEnRaya {
    private static final int NUMERO_JUGADORES = 2;

    private Tablero tablero;
    private Jugador[] jugadores;

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2) throws CuatroEnRayaExcepcion {

        if (jugador1 == null | jugador2 == null){
            throw new CuatroEnRayaExcepcion("Los jugadores no pueden ser nulos.");
        }

        if (jugador1.equals(jugador2) | jugador2.equals(jugador1)) {
            throw new CuatroEnRayaExcepcion("Los jugadores no pueden ser iguales");
        }

        jugadores = new Jugador[NUMERO_JUGADORES];

        jugadores[0] = jugador1;
        jugadores[1] = jugador2;

        tablero = new Tablero();

    }

    private boolean tirar(Jugador jugador) throws CuatroEnRayaExcepcion {
        boolean ganador = false;
        boolean jugadaValida = false;
        int columna;
        do {
            Consola.leerColumna(jugador);
            try {
                tablero.introducirFicha(Consola.leerColumna(jugador),Consola.elegirColorFichas());
                ganador = true;
            }catch (CuatroEnRayaExcepcion cee){
                cee.getMessage();
            }
        }while (!jugadaValida);
        ganador = true;
        return ganador;
    }



    public void jugar() throws CuatroEnRayaExcepcion {


        boolean ganador = true;

        do {
            for (int i = 0; i < jugadores.length; i++) {
                tirar(jugadores[i]);
            }

        }while (tablero.estaVacio() && !ganador);
        System.out.println("ENHORABUENA, s% has ganado!!!");

    }
}
