package ru.mrsu.project.clients.client.clientService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsu.project.clients.client.clientService.api.ClientApi;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

@Slf4j
@RestController
public class ServiceClientWrapper implements ClientApi {
    @Qualifier("ru.mrsu.project.clients.client.clientService.api.ClientApi")
    @Autowired
    private ClientApi service;

    public HashMap<String, Object> getClientService() {
        log.trace("{} method has been mapped", ServiceClientWrapper.class.getMethods()[0].getName());
        try {
            return service.getClientService();
        } catch (FeignException ex) {
            log.error("{} unavailable", ClientApi.class);
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}
