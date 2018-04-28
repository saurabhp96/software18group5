package group5.f4tapi.entity;

import javax.persistence.Entity;

@Entity
public class Requests {

    private Integer table;
    private String message;

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
