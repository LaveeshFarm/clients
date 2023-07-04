package ru.mrsu.project.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mrsu.project.clients.parseData.parseDataImpl.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByAddressId(Integer addressId);
}
