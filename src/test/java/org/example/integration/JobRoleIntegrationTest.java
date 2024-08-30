package org.example.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {

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
                .target("http://localhost:8080/api/job-roles")
                .request()
                .get();

        Assertions.assertNotNull(response);
    }
}
