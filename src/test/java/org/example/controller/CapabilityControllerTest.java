package org.example.controller;

import org.example.controllers.BandController;
import org.example.controllers.CapabilityController;
import org.example.models.Band;
import org.example.models.Capability;
import org.example.services.BandService;
import org.example.services.CapabilityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CapabilityControllerTest {
    CapabilityService capabilityService = Mockito.mock(CapabilityService.class); // Mock the BandService
    private final CapabilityController capabilityController = new CapabilityController(capabilityService); // Inject the BandController to be tested

    @Test
    public void getCapability_Success() throws Exception {
        // Mock the service layer response
        List<Capability> mockCapability = new ArrayList<>();
        mockCapability.add(new Capability(
                1,
                "mockCapability 1"
        ));
        Mockito.when(capabilityService.getAllCapabilities()).thenReturn(mockCapability);

        // Call the method
        Response response = capabilityController.getCapabilities();

        // Verify the result
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(mockCapability, response.getEntity());
    }

    @Test
    public void getCapability_SQLException() throws Exception {
        // Mock the service layer to throw SQLException
        Mockito.when(capabilityService.getAllCapabilities()).thenThrow(new SQLException());

        // Call the method
        Response response = capabilityController.getCapabilities();

        // Verify the result
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
