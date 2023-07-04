package ru.mrsu.project.clients.service.impl;


import org.springframework.stereotype.Service;
import ru.mrsu.project.clients.repository.ClientRepository;
import ru.mrsu.project.clients.parseData.parseDataImpl.Client;
import ru.mrsu.project.clients.service.ClientService;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getClientsByAddress(Integer addressid) {
        return clientRepository.findByAddressId(addressid);
    }

    @Override
    public void setClients() {
        clientRepository.saveAll(getClients());
    }
}