package group5.f4tapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import group5.f4tapi.entity.AllTables;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<AllTables,Long> {

    //http://www.thejavageek.com/2017/02/25/spring-data-jpa-query-methods/
    List<AllTables> findByNumSeatsGreaterThanEqualAndCustomerIsNull(int numSeats);

    @Transactional
    @Modifying
    @Query("UPDATE AllTables SET CustID = :custID WHERE TableID = :tableID")
    int seatCustomer(@Param("tableID") long tableID, @Param("custID") long custID);

    @Transactional
    @Modifying
    @Query("UPDATE AllTables SET CustID = NULL WHERE TableID = :tableID")
    int unseatCustomer(@Param("tableID") long tableID);
}
