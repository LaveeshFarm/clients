package ru.mrsu.project.clients.controllers.services;

import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class ClientControllerServiceFallBack implements ClientControllerServiceFeignClient {
    @Override
    public HashMap<String, Object> getClientService() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("empty", "empty");
        return hm;
    }
}
