package ru.mrsu.project.clients.repository;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import ru.mrsu.project.clients.config.ContainerBase;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;

import java.util.List;

public class AddressRepositoryTest extends ContainerBase {
    @Autowired
    AddressRepository addressRepository;
    @Test
    void checkContainerTest() {
        Assertions.assertEquals(15, this.addressRepository.findAll().size());
    }

    @Test
    void findByFlatNumberGreaterThanEqualTest() {
        Assertions.assertEquals(4, addressRepository.findByFlatNumberGreaterThanEqual(50).size());
    }
}
