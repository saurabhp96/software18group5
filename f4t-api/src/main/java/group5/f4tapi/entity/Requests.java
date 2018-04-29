package group5.f4tapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reqID;
    private long tableID;
    private String message;

    public long getTable() {
        return tableID;
    }

    public void setTable(long tableID) {
        this.tableID = tableID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getReqID() {
        return reqID;
    }

    public void setReqID(long reqID) {
        this.reqID = reqID;
    }
}
