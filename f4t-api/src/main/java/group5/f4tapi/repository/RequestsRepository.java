package group5.f4tapi.repository;

import group5.f4tapi.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepository extends JpaRepository<Requests,Long> {

}
