package ru.mrsu.project.clients.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mrsu.project.clients.exceptions.UnexpectedXMLLocalNameException;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;
import ru.mrsu.project.clients.repository.AddressRepository;
import ru.mrsu.project.clients.service.impl.AddressServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@Import({AddressServiceImpl.class})
public class AddressServiceMockRepositoryTest {
    @Autowired
    AddressService addressService;
    @MockBean
    AddressRepository addressRepository;

    @BeforeEach
    void initDb() {
        when(addressRepository.findByStreet("Blumenweg")).thenReturn(List.of(new Address(1, "test", "Blumenweg", 1,1,1)));
        when(addressRepository.findAllByOrderByHouseAsc()).thenThrow(NullPointerException.class);
    }

    @Test
    void getAddressOrderByHouseAscTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> addressService.getAddressOrderByHouseAsc());
    }

    @Test
    void BlumenwegClientsTest() {
        Address address = addressService.BlumenwegClients().get(0);
        Assertions.assertEquals("Blumenweg", address.getStreet());
        Assertions.assertEquals("test", address.getCity());
    }
}
