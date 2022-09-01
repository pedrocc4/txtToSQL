package bosonit;

import bosonit.entity.Email;
import bosonit.entity.Employee;
import bosonit.entity.Position;
import bosonit.entity.User;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class LectorEmployee {

    private List<Employee> empleados;
    private List<User> users;
    private List<Email> emails;
    private List<Position> posiciones;

    public LectorEmployee() {
        this.empleados = new ArrayList<>();
        this.users = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.posiciones = new ArrayList<>();

    }

    public void cargarPosiciones(String ruta) {
        // Definicion variables necesarias
        String linea;
        String[] lectura;
        FileReader f;
        try {
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);

            // Leemos documento
            while ((linea = b.readLine()) != null && !linea.isEmpty()) {
                lectura = linea.split("\t");
                posiciones.add(new Position(lectura[0], lectura[1], lectura[2]));
            }

            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatos(String ruta) {

        /* NOTA

        Usamos el excel original reducido hasta planta ya que los demas
        datos no son de interes y pueden generar conflicto.
        Es importante una vez se cargue eliminar a mano estas columnas,
        igualmente, descargar en formato .tsv (separado por tab) y renombrar a "archivos"

         */

        // Definicion variables necesarias
        int idEmpleados = 1;
        String linea;
        String[] lectura;
        FileReader f;

        try {
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);

            // Leemos documento
            while ((linea = b.readLine()) != null && !linea.isEmpty()) {
                lectura = linea.split("\t");

                if (!linea.contains("Marca temporal")) { // evitamos header del csv

                    Employee employee = new Employee();
                    // Asignacion de Employee
                    employee.setId(idEmpleados++);
                    employee.setEmail(lectura[1]);
                    // FIXME en nombre almacenamos nombre y apellidos
                    employee.setName(lectura[2]);
                    // TODO remote no se asigna
                    // TODO Estandarizar fecha en excel y descomentar esta linea (agregar ParseException)
                    // employee.setIncorporationDate(
                    // new SimpleDateFormat("dd/MM/yyyy").parse(lectura[3].split(" ")[0]));
                    employee.setCheckinTimePreference(lectura[5].split(" ")[0]);
                    employee.setCheckoutTimePreference(lectura[6].split(" ")[0]);

                    // Asignacion de CAR y SUBCAR con los datos actuales en data.sql
                    // TODO mejorar apartado CAR y SUBCAR
                    if (lectura[11].contains("Formación")) {
                        employee.setFk_car(1);
                    } else if (lectura[11].contains("Finance")) {
                        employee.setFk_car(2);
                    } else if (lectura[11].contains("Desarrollo")) {
                        employee.setFk_car(3);
                    }

                    if (lectura[12].contains("Estudiantes")) {
                        employee.setFk_subcar(1);
                    } else if (lectura[12].contains("Consultan")) {
                        employee.setFk_subcar(2);
                    } else if (lectura[12].contains("Developer")) {
                        employee.setFk_subcar(3);
                    }

                    // User coincide con employee.id
                    employee.setFk_user(employee.getId());

                    // Busamos id position
                    for (Position position : posiciones) {
                        if (position.getFloor().charAt(0) == lectura[lectura.length - 1].charAt(1)
                                && position.getPosition().equals(lectura[lectura.length - 2])) {
                            employee.setFk_position(Integer.parseInt(position.getId()));
                        }
                    }

                    empleados.add(employee);

                    // Definimos users, emails y posiciones
                    String userName = employee.getEmail().split("@")[0];
                    users.add(new User(employee.getId(), userName, employee.getEmail()));
                    emails.add(new Email(employee.getId(), employee.getEmail()));
                }
            }

            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void toTxt() throws FileNotFoundException {
        FileOutputStream txt = new FileOutputStream("datosSQL.txt");
        try (PrintWriter pw = new PrintWriter(txt)) {

            // exportamos empleados
            empleados.stream()
                    .map(Employee::toSQL)
                    .forEach(pw::println);

            pw.println();

            // exportamos users
            users.stream()
                    .map(User::toSQL)
                    .forEach(pw::println);

            pw.println();

            // exportamos emails
            emails.stream()
                    .map(Email::toSQL)
                    .forEach(pw::println);

            pw.println();
        }
    }
}
