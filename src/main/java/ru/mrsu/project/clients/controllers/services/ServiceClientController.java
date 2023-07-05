package ru.mrsu.project.clients.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ServiceClientController {
    @Qualifier("ru.mrsu.project.clients.controllers.services.ClientControllerServiceFeignClient")
    @Autowired
    private ClientControllerServiceFeignClient service;

    @GetMapping("internal/clients/service")
    public HashMap<String, Object> getServiceClient() {
        return service.getClientService();
    }

}
