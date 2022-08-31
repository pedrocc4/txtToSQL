package bosonit;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String surname;
    private Date incorporationDate;
    private Date intershipEndDate;
    private String checkinTimePreference;
    private String checkoutTimePreference;
    private String email;
    private String comments;
    private Boolean remote;
    private int fk_user;
    private int fk_position;
    private int fk_office;
    private int fk_province;
    private int fk_car;
    private int fk_subcar;
    private int fk_company;

    public Employee() {
        this.intershipEndDate = Date.from(Instant.now()); //FIXME cambiar ¿es fecha fin practicas?
        this.fk_company = 1;
        this.fk_office = 1;
        this.comments = "comments";
        this.remote = false;
        this.surname = "por asignar";
    }

    public String toSQL() {
        return "INSERT IGNORE" +
                " INTO mstr_employee (" +
                "id,  name, checkin_time_preference, checkout_time_preference, comments," +
                " incorporation_date, intership_end_date, remote, surname, fk_office, fk_user," +
                " fk_car, fk_subcar, fk_company, fk_position)" +
                " VALUES ('" + id + "', '" + name + "', '" + checkinTimePreference + "', '" + checkoutTimePreference +
                "', '" + comments + "', '" + incorporationDate + "', '" + intershipEndDate + "', '"  +
                "', '" + remote + "', '" + surname + "', '" + fk_office + "', '" + fk_user + "', '" + fk_car +
                "', '" + fk_subcar + "', '" + fk_company + "', '" + fk_position + ");";
    }
}
