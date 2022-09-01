package lector;

import java.io.FileNotFoundException;

public class Proceso {
    public static void main(String[] args) throws FileNotFoundException {

        LectorAreaTech lectorAreaTech = new LectorAreaTech();
        lectorAreaTech.cargarDatos("areaTech.txt");
        System.out.println("Archivo cargado y procesado");
        lectorAreaTech.toTxt();
        System.out.println("Fichero creado");
    }
}
