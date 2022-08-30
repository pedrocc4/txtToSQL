package lector;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class Lector {

    private List<Area> listaAreas = new ArrayList<>();
    private List<Technology> listaTechnology = new ArrayList<>();

    // Declaramos las areas previamente conocidas
    private static final Set<String> LINEAS = Set.of(
            "Backend", "Big Data", "Data Science", "Data Management",
            "Business Intelligence", "Frontend", "DevOps");

    public void cargarDatos(String ruta) {

        // Definicion variables necesarias
        int idArea = 1;
        int idTechnology = 1;
        String linea;
        FileReader f;

        try {
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);

            // Leemos documento
            while ((linea = b.readLine()) != null) {
                if (!linea.isEmpty()) {
                    // Caso areas
                    if (linea.contains("Big Data")) {
                        // en concreto Big Data genera problemas
                        // puede que ser por ser la primera
                        listaAreas.add(new Area(idArea++, "Big Data"));
                    } else if (linea.equals("Backend") ||
                            linea.equals("Data Science") ||
                            linea.equals("Data Management") ||
                            linea.equals("Business Intelligence") ||
                            linea.equals("DevOps") ||
                            linea.equals("Frontend")) {
                        listaAreas.add(new Area(idArea++, linea));
                    } else {
                        listaTechnology.add(
                                new Technology(idTechnology++, linea,
                                        listaAreas.get(listaAreas.size() - 1).getId()));
                    }
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

            // exportamos areas
            listaAreas.stream()
                    .map(Area::toSQL)
                    .forEach(pw::println);

            pw.println();

            // exportamos tecnologias
            listaTechnology.stream()
                    .map(Technology::toSQL)
                    .forEach(pw::println);
        }
    }
}
