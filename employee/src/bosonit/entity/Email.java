package bosonit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
private int id;
private String email;

    public String toSQL() {
        return "INSERT IGNORE" +
                " INTO mstr_email" +
                " VALUES ('" + id + "', '" + email + "', '" + 1 + "', '" + id + ");";
    }

}
