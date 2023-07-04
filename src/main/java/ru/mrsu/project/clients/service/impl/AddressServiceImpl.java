package ru.mrsu.project.clients.service.impl;


import org.springframework.stereotype.Service;
import ru.mrsu.project.clients.repository.AddressRepository;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;
import ru.mrsu.project.clients.service.AddressService;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address getOneAddress(Integer id) {
        return addressRepository.findAddressById(id);
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