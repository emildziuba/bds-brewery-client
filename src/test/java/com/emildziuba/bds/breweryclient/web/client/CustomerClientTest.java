package com.emildziuba.bds.breweryclient.web.client;

import com.emildziuba.bds.breweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void getCustomerByUuid() {
        CustomerDto customer = client.getCustomerByUuid(UUID.randomUUID());
        assertNotNull(customer);
    }

    @Test
    void saveNewCustomer() {
        URI uri = client.saveNewCustomer(CustomerDto.builder().name("test").build());
        assertNotNull(uri);
        System.out.println(uri.toString());
    }

    @Test
    void updateCustomer() {
        client.updateCustomer(UUID.randomUUID(), CustomerDto.builder().name("test").build());
    }

    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}