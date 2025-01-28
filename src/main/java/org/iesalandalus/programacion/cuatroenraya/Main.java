package org.iesalandalus.programacion.cuatroenraya;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
public class Main {

	public static void main(String[] args) {
		Jugador jugador1 = new Jugador("Jugador 1", Ficha.AZUL);
		Jugador jugador2 = new Jugador("Jugador 2", Ficha.VERDE);

		CuatroEnRaya juego = new CuatroEnRaya(jugador1, jugador2);

		juego.jugar();
	}

}