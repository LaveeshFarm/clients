package ru.mrsu.project.clients.controllers;

import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;
import ru.mrsu.project.clients.parseData.parseDataImpl.Client;
import ru.mrsu.project.clients.service.AddressService;
import ru.mrsu.project.clients.service.ClientService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;
    private final ClientService clientService;

    public AddressController(AddressService addressService, ClientService clientService) {
        this.addressService = addressService;
        this.clientService = clientService;
    }

    @GetMapping("/internal/addresses")
    public List<Address> getAddress() {
        return addressService.getAddress();
    }

    @GetMapping("/internal/address/{id}")
    public Address getOneAddress(@PathVariable("id") Integer id) {
        return addressService.getOneAddress(id);
    }

    @GetMapping("/add/addresses")
    public void setAddress() { addressService.setAddress();}

    @GetMapping("/internal/blumenweg/clients")
    public List<Client> getBlumenwegClients() {
        List<Client> clients = new ArrayList<>();
        addressService.BlumenwegClients().forEach(address -> clients.addAll(address.getClientId()));
        return clients;
    }
    @GetMapping("/internal/addresses/byhouse")
    public List<Address> getAddressesOrderByHouseAsc() {
        return addressService.getAddressOrderByHouseAsc();
    }

    @GetMapping("/internal/adresses/getdto")
    public List<AddressDTO.Response.AddressStreetHouseName> getStreetHouseAndNameClient() {
        List<Address> addresses = addressService.getAddressFloorGreaterTen();
        List<Client> clients = new ArrayList<>();
        addresses.forEach(address -> clients.addAll(clientService.getClientsByAddress(address.getId())));
        List<AddressDTO.Response.AddressStreetHouseName> addressesStreetHouseName = new ArrayList<>();
        clients.forEach(client -> addresses.forEach(address -> {
            if (address.getId().equals(client.getAddressId())) {
                addressesStreetHouseName.add(new AddressDTO.Response.AddressStreetHouseName(client.getName(), address.getStreet(), address.getHouse()));
            }
        }));
        return addressesStreetHouseName;
    }

    @GetMapping("/internal/adresses/getdtoflat")
    public List<AddressDTO.Response.AddressStreetHouseNameFlatnumber> getStreetHouseNameFlatnumberClient() {
        List<Address> addresses = addressService.getAddressFloorGreaterTen();
        List<Client> clients = new ArrayList<>();
        addresses.forEach(address -> clients.addAll(clientService.getClientsByAddress(address.getId())));
        List<AddressDTO.Response.AddressStreetHouseNameFlatnumber> addressesStreetHouseName = new ArrayList<>();
        clients.forEach(client -> addresses.forEach(address -> {
            if (address.getId().equals(client.getAddressId())) {
                addressesStreetHouseName.add(new AddressDTO.Response.AddressStreetHouseNameFlatnumber(client.getName(), address.getStreet(), address.getHouse(), address.getFlatNumber()));
            }
        }));
        return addressesStreetHouseName;
    }
    public enum AddressDTO {;
        private interface Id { Integer getId(); }
        private interface Name { String getName(); }
        private interface Street { String getStreet(); }
        private interface House { Integer getHouse(); }
        private interface FlatNumber { Integer getFlatNumber(); }

        public enum Response{;
            @Value
             public static class AddressStreetHouseName implements Name, Street, House {
                String name;
                String street;
                Integer house;
            }
            @Value
            public static class AddressStreetHouseNameFlatnumber implements Name, Street, House, FlatNumber {
                String name;
                String street;
                Integer house;
                Integer flatNumber;
            }
        }
    }
}
