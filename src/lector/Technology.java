package lector;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Technology {
    private int id;
    private String name;
    private int fk_area;

    public String toSQL() {
        return "INSERT IGNORE INTO lkup_technology(id, name, fk_area) " +
                "VALUES('" + id + "', '" + name + "', " + "'" + fk_area + "');";
    }

}
