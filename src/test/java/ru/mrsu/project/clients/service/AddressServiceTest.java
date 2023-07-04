package ru.mrsu.project.clients.service;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Test;
import ru.mrsu.project.clients.config.ContainerBase;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;
import ru.mrsu.project.clients.repository.AddressRepository;
import ru.mrsu.project.clients.service.impl.AddressServiceImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Import(AddressServiceImpl.class)
public class AddressServiceTest extends ContainerBase {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;

    @Test
    void BlumenwegClientsTest() {
        List<Address> addressesTest = addressRepository.findAllById(List.of(1,2,3,10,11));
        Assertions.assertEquals(addressesTest,addressService.BlumenwegClients());
    }

    @Test
    void getAddressFloorGreaterTenTest() {
        List<Address> addressesTest = addressRepository.findAllById(List.of(2,5));
        List<Address> adresses = addressService.getAddressFloorGreaterTen();
        AtomicReference<Integer> iter = new AtomicReference<>((Integer) 0);
        addressesTest.forEach(address -> {
            if(adresses.contains(address)) {
                iter.getAndSet(iter.get() + 1);
            }
        });
        if(!iter.get().equals(0))
            Assertions.fail();
//        Assertions.assertNotEquals(addressesTest,adresses);
    }
}
