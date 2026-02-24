package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.CuatroEnRaya;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class Main {
	
	public static void main(String[] args) {

        try {
            Jugador jugador1 = Consola.leerJugador();

            Ficha fichaContraria = (jugador1.colorFichas() == Ficha.AZUL) ? Ficha.VERDE : Ficha.AZUL;

            Jugador jugador2 = Consola.leerJugador(fichaContraria);

            CuatroEnRaya cuatroEnRaya = new CuatroEnRaya(jugador1, jugador2);

            cuatroEnRaya.jugar();

        } catch (CuatroEnRayaExcepcion e) {
            System.out.println("ERROR:"+ e.getMessage());
        }
    }

}
