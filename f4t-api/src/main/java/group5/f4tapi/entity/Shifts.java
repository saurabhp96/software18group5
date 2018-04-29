package group5.f4tapi.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
public class Shifts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shiftId;
    private String day;
    private Time startTime;
    private Time endTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "EmpShift",
                joinColumns = @JoinColumn(name="ShiftID", referencedColumnName = "ShiftID"),
                inverseJoinColumns = @JoinColumn(name = "EmpID",referencedColumnName = "EmpID"))
    private Set<Employee> empShifts;

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
