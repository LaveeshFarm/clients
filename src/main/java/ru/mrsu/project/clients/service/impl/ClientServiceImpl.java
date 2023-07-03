package ru.mrsu.project.clients.service.impl;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.mrsu.project.clients.jpa.ClientRepository;
import ru.mrsu.project.clients.parseDara.parseDataImpl.Client;
import ru.mrsu.project.clients.service.ClientService;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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