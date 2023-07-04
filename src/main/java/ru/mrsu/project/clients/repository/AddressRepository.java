package ru.mrsu.project.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer>  {
    List<Address> findByStreet(String street);
    List<Address> findAllByOrderByHouseAsc();
    List<Address> findByFlatNumberGreaterThanEqual(Integer flatNumber);
    Address findAddressById(Integer id);
}
