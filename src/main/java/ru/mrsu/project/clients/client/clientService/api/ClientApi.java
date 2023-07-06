package ru.mrsu.project.clients.client.clientService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@FeignClient(value = "clientService",
        url = "http://localhost:8090/",
        fallback = ClientApiFallBack.class)
public interface ClientApi {
    @GetMapping("/internal/clients/service")
    HashMap<String, Object> getClientService();
}
