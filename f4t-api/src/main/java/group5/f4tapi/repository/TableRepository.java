package group5.f4tapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import group5.f4tapi.entity.AllTables;

@Repository
public interface TableRepository extends JpaRepository<AllTables,Long> {


}
