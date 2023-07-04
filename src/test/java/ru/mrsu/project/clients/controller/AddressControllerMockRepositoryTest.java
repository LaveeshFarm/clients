package ru.mrsu.project.clients.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.mrsu.project.clients.controllers.AddressController;
import ru.mrsu.project.clients.exceptions.UnexpectedXMLLocalNameException;
import ru.mrsu.project.clients.parseData.parseDataImpl.Address;
import ru.mrsu.project.clients.parseData.parseDataImpl.Client;
import ru.mrsu.project.clients.repository.AddressRepository;
import ru.mrsu.project.clients.repository.ClientRepository;
import ru.mrsu.project.clients.service.AddressService;
import ru.mrsu.project.clients.service.ClientService;
import ru.mrsu.project.clients.service.impl.AddressServiceImpl;
import ru.mrsu.project.clients.service.impl.ClientServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
@ExtendWith(SpringExtension.class)
@Import({AddressServiceImpl.class, ClientServiceImpl.class})
@AutoConfigureMockMvc
public class AddressControllerMockRepositoryTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    ClientService clientService;
    @MockBean
    ClientRepository clientRepository;

    @BeforeEach
    void initDb() {
        when(addressRepository.findByStreet("Blumenweg")).thenReturn(List.of(new Address(1, "test",
                "Blumenweg", 1, 1, 1,
                List.of(new Client(1, "Test1", "ABCD123", 1),
                        new Client(2, "Test2", "EFGH456", 1)))));
        when(addressRepository.findAllByOrderByHouseAsc()).thenThrow(NullPointerException.class);
    }

    @Test
    void getBlumenwegClientsTest() throws Exception {
        mvc.perform(get("/internal/blumenweg/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].name").value("Test2"))
                .andExpect(jsonPath("$[0].addressId").value(1))
                .andExpect(jsonPath("$[1].addressId").value(1))
                .andDo(print());
    }

    @Test
    void getAddressesOrderByHouseAscTestIsOk() throws Exception {
        mvc.perform(get("/internal/address/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAddressesOrderByHouseAscTestIs() throws Exception {
        mvc.perform(get("/internal/address/{id}", "abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
