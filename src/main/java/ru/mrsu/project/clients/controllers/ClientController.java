package ru.mrsu.project.clients.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsu.project.clients.parseDara.parseDataImpl.Client;
import ru.mrsu.project.clients.service.ClientService;

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
