package org.iesalandalus.programacion.cuatroenraya.modelo;


import org.iesalandalus.programacion.cuatroenraya.vista.Consola;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import java.util.Objects;

public class CuatroEnRaya {
    private static final int NUMERO_JUGADORES = 2;

    private Tablero tablero;
    private Jugador[] jugadores;

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2) throws CuatroEnRayaExcepcion {

        if (jugador1 == null || jugador2 == null){
            throw new CuatroEnRayaExcepcion("Los jugadores no pueden ser nulos.");
        }

        if (jugador1.equals(jugador2) || jugador2.equals(jugador1)) {
            throw new CuatroEnRayaExcepcion("Los jugadores no pueden ser iguales");
        }

        jugadores = new Jugador[NUMERO_JUGADORES];

        jugadores[0] = jugador1;
        jugadores[1] = jugador2;

        tablero = new Tablero();

    }

    private boolean tirar(Jugador jugador) throws CuatroEnRayaExcepcion {
        boolean jugadaValida = false;
        boolean haGanado = false;

        while (!jugadaValida) {
            try {
                int columna = Consola.leerColumna(jugador);
                haGanado = tablero.introducirFicha(columna, jugador.colorFichas());
                jugadaValida = true;
            } catch (CuatroEnRayaExcepcion e) {
                System.out.println("La fila esta llena"+e.getMessage());
            }
        }

        return haGanado;
    }



    public void jugar() throws CuatroEnRayaExcepcion {
        boolean ganador = false;
        int turno = 0;

        while (!tablero.estaLleno() && !ganador) {

            Jugador jugadorActual = jugadores[turno % NUMERO_JUGADORES];

            System.out.println(tablero);

            ganador = tirar(jugadorActual);

            if (!ganador) {
                turno++;
            }
        }

        System.out.println(tablero);

        if (ganador) {
            Jugador ganadorFinal = jugadores[turno % NUMERO_JUGADORES];
            System.out.println("ENHORABUENA, "+ganadorFinal+" has ganado!!!" );
        } else {
            System.out.println("No hay más casillas libres. Empate.");
        }
    }
}
