package org.iesalandalus.programacion.cuatroenraya;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class Main {

	public static void main(String[] args) {

		/**1 creará los jugadores, creará una instancia de CuatroEnRaya con ambos jugadores y los pondrá a jugar.**/
		Jugador jugador1 = Consola.leerJugador();
		Jugador jugador2 = Consola.leerJugador(jugador1.colorFichas() == Ficha.AZUL ? Ficha.VERDE : Ficha.AZUL);

		CuatroEnRaya juego = new CuatroEnRaya(jugador1, jugador2);

		juego.jugar();
	}

}