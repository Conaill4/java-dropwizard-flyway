package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {

    private static final DropwizardAppExtension<TestConfiguration> APP =
            new DropwizardAppExtension<>(TestApplication.class);

    private Client client;

    @BeforeEach
    public void setUp() {
        client = createClientWithTimeout();
    }

    private Client createClientWithTimeout() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 10000);  // 10 seconds connection timeout
        clientConfig.property(ClientProperties.READ_TIMEOUT, 10000);     // 10 seconds read timeout
        return ClientBuilder.newClient(clientConfig);
    }

    @Test
    public void getAllJobRoles_ResponseShouldNotBeNull() {
        Client client = APP.client();

        String jsonPayload = "{\"email\":\"admin@kainos.com\",\"password\":\"$2a$10$hK06hO1rep2GeyyksoEA1ue01/ypRDpaYneXFTtaHtdT3DAFX1ayC\"}";

        Response response = client
                .target("http://localhost:8080/api/auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(jsonPayload));

        Assertions.assertNotNull(response);
    }
}
