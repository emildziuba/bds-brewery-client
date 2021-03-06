package com.emildziuba.bds.breweryclient.web.client;

import com.emildziuba.bds.breweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix = "bds.brewery", ignoreUnknownFields = false)
@Component
public class CustomerClient {

    RestTemplate restTemplate;
    String apihost;
    String CUSTOMER_PATH_V1 = "/api/v1/customer";

    public CustomerClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public CustomerDto getCustomerByUuid(UUID uuid) {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + "/" + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto dto) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1 , dto);
    }

    public void updateCustomer(UUID uuid, CustomerDto dto) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + "/" + uuid.toString(), dto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + "/" + uuid.toString());
    }

}
