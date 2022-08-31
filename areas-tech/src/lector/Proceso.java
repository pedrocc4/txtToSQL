package lector;

import java.io.FileNotFoundException;

public class Proceso {
    public static void main(String[] args) throws FileNotFoundException {

        Lector lector = new Lector();
        lector.cargarDatos("archivo.txt");
        System.out.println("Archivo cargado y procesado");
        lector.toTxt();
        System.out.println("Fichero creado");
    }
}
