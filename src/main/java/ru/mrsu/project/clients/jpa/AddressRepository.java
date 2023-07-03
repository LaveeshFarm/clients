package ru.mrsu.project.clients.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mrsu.project.clients.parseDara.parseDataImpl.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer>  {
    List<Address> findByStreet(String street);
    List<Address> findAllByOrderByHouseAsc();
    List<Address> findByFlatNumberGreaterThanEqual(Integer flatNumber);
}
