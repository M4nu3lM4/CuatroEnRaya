package org.iesalandalus.programacion.cuatroenraya.vista;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;
public class Consola {

private Consola(){}

    public static String leerNombre(){

    String nombre = "";

    do{
        System.out.println("Introduce el nombre del jugador");
        nombre = Entrada.cadena();
    }while (nombre.trim().equals(""));
    return nombre;
    }

    public static Ficha elegirColorFichas(){

        Ficha colorFichas = null;

        do{
            System.out.println("Introduce el color de tus fichas");
            String color =Entrada.cadena();

            if(color.equals("A") || color.toUpperCase().equals(("AZUL"))){
                colorFichas = Ficha.AZUL;
            } else if (color.equals("V") || color.toUpperCase().equals(("VERDE"))) {
                colorFichas = Ficha.VERDE;
            }
        }while (colorFichas == null);
        return colorFichas;
    }

    public static Jugador leerJugador(){

        String nombre = leerNombre();
        Ficha color= elegirColorFichas();
        return new Jugador(nombre, color);




    }

    public static Jugador leerJugador(Ficha colorFicha){

        String nombre = leerNombre();
        Ficha color = colorFicha;
        return new Jugador(nombre, color);
    }

    public static int leerColumna(Jugador jugador){

    int columna = 0;
        do{
            System.out.print(jugador.nombre() + "Introduce la columna en la que deseas introducir la ficha(0 - 6):");
            columna = Entrada.entero();
        }while(columna < 0 || columna > 7);
        return columna;
    }


}
