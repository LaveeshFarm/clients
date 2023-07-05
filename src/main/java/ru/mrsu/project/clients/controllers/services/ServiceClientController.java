package ru.mrsu.project.clients.controllers.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

@RestController
public class ServiceClientController {
    @Qualifier("ru.mrsu.project.clients.controllers.services.ClientControllerServiceFeignClient")
    @Autowired
    private ClientControllerServiceFeignClient service;

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("internal/clients/service")
    public HashMap<String, Object> getServiceClient() {
        log.trace("{} method has been mapped", ServiceClientController.class.getMethods()[0].getName());
        try {
            return service.getClientService();
        } catch (RuntimeException e) {
            log.error("{} has crashed", ClientControllerServiceFeignClient.class);
            return new HashMap<>();
        }
    }

}
