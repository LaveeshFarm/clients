package ru.mrsu.project.clients.service;


import ru.mrsu.project.clients.parseDara.parseDataImpl.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getClients();
    public List<Client> getClientsByAddress(Integer addressId);

    public void setClients();
}
