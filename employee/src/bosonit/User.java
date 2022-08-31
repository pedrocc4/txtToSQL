package bosonit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    //private Set<String> permissions = new HashSet<>();

    public User(int id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = "$2a$10$PGxotTZj1i5Msv8chREtvujxj33YpEf0PutQ1gas8vW1ThVrhctNW";
    }

    public String toSQL() {
        Random random = new Random();
        return "INSERT IGNORE" +
                " INTO mstr_user (" +
                "id, email, last_modified_user, password, reset_password_token, user_name)" +
                " VALUES ('" + id + "', '" + email + "', '" + "now()" + "', '" + password +
                "', '" + random.nextInt(50000 - 5000) + 5000 + "', '" + userName + ");";
    }
}
