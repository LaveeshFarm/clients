package ru.mrsu.project.clients.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mrsu.project.clients.parseDara.parseDataImpl.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByAddressId(Integer addressId);
}
