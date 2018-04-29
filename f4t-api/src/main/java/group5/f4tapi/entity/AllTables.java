package group5.f4tapi.entity;
import javax.persistence.*;

@Entity
public class AllTables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableId;
    private Integer numSeats;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CustID")
    private Customer customer;


    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer id) {
        this.tableId = id;
    }

    public Integer getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(Integer numSeats) {
        this.numSeats = numSeats;
    }
}
