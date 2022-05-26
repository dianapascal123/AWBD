package proiect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Override
    <S extends Address> S save(S s);
}
