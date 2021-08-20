package com.emildziuba.bds.breweryclient.web.client;

import com.emildziuba.bds.breweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(ignoreUnknownFields = false, prefix = "bds.brewery")
@Component
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer";
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + "/" + id.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto dto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, dto);
    }

    public void updateBeer(UUID uuid, BeerDto dto) {
        restTemplate.put(apihost + BEER_PATH_V1 + "/" + uuid.toString(), dto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apihost + BEER_PATH_V1 + "/" + uuid.toString());
    }
}