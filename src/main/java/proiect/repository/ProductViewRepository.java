package proiect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proiect.entity.Product;
import proiect.entity.ProductView;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductViewRepository extends JpaRepository<ProductView, Long> {
    @Override
    List<ProductView> findAll();

    @Query("select pv from ProductView pv where pv.product = :product")
    Optional<ProductView> findByProduct(@Param("product") Product product);

    @Override
    <S extends ProductView> S save(S s);
}
