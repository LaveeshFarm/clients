package ru.mrsu.project.clients.service;

import ru.mrsu.project.clients.parseData.parseDataImpl.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAddress();
    public Address getOneAddress(Integer id);
    public List<Address> BlumenwegClients();
    public List<Address> getAddressOrderByHouseAsc();
    public List<Address> getAddressFloorGreaterTen();
    void setAddress();
}
