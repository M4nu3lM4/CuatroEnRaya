package org.iesalandalus.programacion.cuatroenraya.modelo;
import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Casilla;
import java.util.Objects;

public record Jugador(String nombre, Ficha colorFichas) {

    /**1º Constructor canónico con validaciones **/
    public Jugador {
        validarNombre(nombre);
        validarColorFichas(colorFichas);
    }

    /**2º Metodo para validar el nombre **/
    private void validarNombre(String nombre) {
        if (nombre == null ) {
            throw new NullPointerException("El nombre no puede ser nulo.");
        }
        if(nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
    }
    /**3º Metodo para validar el color de las fichas **/
    private static void validarColorFichas(Ficha colorFichas) {
        if (colorFichas == null) {
            throw new NullPointerException("El color de las fichas no puede ser nulo.");
        }
    }

    /**4º Metodo toString sobreescrito utilizando String.format **/
    @Override
    public String toString() {
        return String.format("%s (%s)", this.nombre, this.colorFichas.toString());
    }
}


