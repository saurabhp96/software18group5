package group5.f4tapi.repository;

import group5.f4tapi.entity.Shifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shifts,Long> {
    List<Shifts> findByEmpShifts_EmpID(long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EmpShifts (EmpID,ShiftID) VALUES(:empid,:shiftid)",nativeQuery = true)
    int addShiftToEmployee(@Param("empid")long empid, @Param("shiftid")long shiftid);
}
