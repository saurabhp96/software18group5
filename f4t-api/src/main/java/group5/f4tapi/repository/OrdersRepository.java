package group5.f4tapi.repository;

import group5.f4tapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long>, OrdersRepositoryCustom {
}

