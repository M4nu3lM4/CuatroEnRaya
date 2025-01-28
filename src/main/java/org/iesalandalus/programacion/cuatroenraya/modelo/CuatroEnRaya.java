package org.iesalandalus.programacion.cuatroenraya.modelo;
import org.iesalandalus.programacion.cuatroenraya.vista.*;

public class CuatroEnRaya {
    private static final int NUMERO_JUGADORES = 2;

    private Jugador[] jugadores;
    private Tablero tablero;


    public CuatroEnRaya(Jugador jugador1, Jugador jugador2){

        this.jugadores = new Jugador[NUMERO_JUGADORES];
        this.jugadores[0] = jugador1;
        this.jugadores[1] = jugador2;
        this.tablero = new Tablero();

    }

    private boolean tirar(Jugador jugador) {
        boolean esValida = false;
        boolean esGanador = false;

        do {
            System.out.println(tablero);
            int columna = Consola.leerColumna(jugador);
            try {
                esGanador = this.tablero.introducirFicha(columna, jugador.colorFichas());
                esValida = true;
            } catch (CuatroEnRayaExcepcion e) {
                System.out.println(e.getMessage());
            }
        } while (!esValida);
        return esGanador;
    }

    public void jugar() {
        System.out.println("\n COMIENZA EL JUEGO");
        int turno = (int) (Math.random()*2);
        boolean esGanador = false;
        Jugador ganador = null;
        while(!esGanador && this.tablero.estaLleno()){
            esGanador = tirar(jugadores[turno]);
            if (esGanador){
                ganador = jugadores[turno];
            }
            turno = (turno + 1) % 2;
        }
        System.out.println(tablero);
        if (esGanador){
            System.out.println("ha ganado el jugador "+ganador);
        }else{
            System.out.println("Fin del juego: Empate");
        }
    }
}