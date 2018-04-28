package group5.f4tapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import group5.f4tapi.entity.AllTables;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<AllTables,Long> {

    //http://www.thejavageek.com/2017/02/25/spring-data-jpa-query-methods/
    List<AllTables> findByNumSeatsGreaterThanEqualAndOccupied(int numSeats,int occupied);


}
