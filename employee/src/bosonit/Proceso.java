package bosonit;

import java.io.FileNotFoundException;

public class Proceso {
    public static void main(String[] args) throws FileNotFoundException {
        LectorEmployee lectorEmployee = new LectorEmployee();
        lectorEmployee.cargarPosiciones("posiciones.txt");
        lectorEmployee.cargarDatos("employees.tsv");
        System.out.println("Carga de empleados completada");
        lectorEmployee.toTxt();
        System.out.println("Archivo exportado");
    }
}
