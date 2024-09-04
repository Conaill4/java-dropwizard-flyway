package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

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

        Response response = client
                .target("http://localhost:8080/api/auth/login")
                .request()
                .get();

        Assertions.assertNotNull(response);
    }
}
