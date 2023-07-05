package ru.mrsu.project.clients.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsu.project.clients.parseData.parseDataImpl.Client;
import ru.mrsu.project.clients.service.ClientService;

import java.util.HashMap;
import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/internal/clients")
    public List<Client> getClient() {
        return clientService.getClients();
    }

    @GetMapping("/add/clients")
    public void setClient() { clientService.setClients();}
}
