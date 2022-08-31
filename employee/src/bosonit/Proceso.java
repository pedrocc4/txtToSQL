package bosonit;

import java.io.FileNotFoundException;

public class Proceso {
    public static void main(String[] args) throws FileNotFoundException {
        Lector lector = new Lector();
        lector.cargarDatos("archivos.tsv");
        System.out.println("Carga completada");
        lector.toTxt();
        System.out.println("Archivo exportado");
    }
}
