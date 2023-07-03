package ru.mrsu.project.clients.service.impl;


import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.mrsu.project.clients.jpa.AddressRepository;
import ru.mrsu.project.clients.parseDara.parseDataImpl.Address;
import ru.mrsu.project.clients.parseDara.parseDataImpl.Client;
import ru.mrsu.project.clients.service.AddressService;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    private final ResourceLoader resourceLoader;
    private final AddressRepository addressRepository;
    public AddressServiceImpl(ResourceLoader resourceLoader, AddressRepository addressRepository) {
        this.resourceLoader = resourceLoader;
        this.addressRepository = addressRepository;
    }
    @Override
    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    @Override
    public void setAddress() {
        addressRepository.saveAll(getAddress());
    }

    public List<Address> BlumenwegClients() { return addressRepository.findByStreet("Blumenweg");}

    @Override
    public List<Address> getAddressOrderByHouseAsc() {
        return addressRepository.findAllByOrderByHouseAsc();
    }

    @Override
    public List<Address> getAddressFloorGreaterTen() {
        return addressRepository.findByFlatNumberGreaterThanEqual(10);
    }
}