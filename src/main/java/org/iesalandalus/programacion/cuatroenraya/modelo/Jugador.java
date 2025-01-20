package org.iesalandalus.programacion.cuatroenraya.modelo;
import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Casilla;
import java.util.Objects;

public record Jugador(String nombre, Ficha colorFichas) {


    private void validarNombre(String nombre){
        if(nombre == null){
            Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        }

    }
    private void validarColorFichas(Ficha colorFichas){
        if(colorFichas == null){
            Objects.requireNonNull(colorFichas,"El color de las fichas no puede ser nulo.");
        }
    }

    @Override
    public String toString() {
        return String.format("[colorfichas=%s]", colorFichas);
    }
}
