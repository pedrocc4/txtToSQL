package bosonit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private String id;
    private String floor;
    private String position;
    private int fk_office;

    public Position(String id, String floor, String position) {
        this.id = id;
        this.floor = floor;
        this.position = position;
        this.fk_office = 1;
    }

    public String toSQL() {
        return "INSERT IGNORE" +
                " INTO lkup_position (id, floor, position, fk_office)" +
                " VALUES ('" + id + "', '" + floor + "', '" + position + "', '" + fk_office + ");";
    }
}
