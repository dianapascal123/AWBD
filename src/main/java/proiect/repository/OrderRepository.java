package proiect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.entity.PerfumeOrder;


public interface OrderRepository extends JpaRepository<PerfumeOrder, Long> {
    @Override
    <S extends PerfumeOrder> S save(S s);

    @Override
    long count();
}
