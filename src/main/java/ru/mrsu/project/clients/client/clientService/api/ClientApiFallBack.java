package ru.mrsu.project.clients.client.clientService.api;

import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class ClientApiFallBack implements ClientApi {
    @Override
    public HashMap<String, Object> getClientService() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("empty", "empty");
        return hm;
    }
}
