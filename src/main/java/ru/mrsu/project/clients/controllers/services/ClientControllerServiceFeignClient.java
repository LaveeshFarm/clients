package ru.mrsu.project.clients.controllers.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@FeignClient(value = "clientService",
        url = "http://localhost:8090/",
        fallback = ClientControllerServiceFallBack.class)
public interface ClientControllerServiceFeignClient {
    @GetMapping("/client")
    HashMap<String, Object> getClientService();
}
