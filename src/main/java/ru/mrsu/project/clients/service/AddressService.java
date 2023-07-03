package ru.mrsu.project.clients.service;

import ru.mrsu.project.clients.parseDara.parseDataImpl.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAddress();
    public List<Address> BlumenwegClients();
    public List<Address> getAddressOrderByHouseAsc();
    public List<Address> getAddressFloorGreaterTen();
    void setAddress();
}
