package org.example.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.controllers.BandController;
import org.example.models.Band;
import org.example.services.BandService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandControllerTest {
    BandService bandService = Mockito.mock(BandService.class); // Mock the BandService
    private final BandController bandController = new BandController(bandService); // Inject the BandController to be tested

    @Test
    public void getBands_Success() throws Exception {
        // Mock the service layer response
        List<Band> mockBands = new ArrayList<>();
        mockBands.add(new Band(
                1,
                "Band 1"
        ));
        when(bandService.getAllBands()).thenReturn(mockBands);

        // Call the method
        Response response = bandController.getBands();

        // Verify the result
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(mockBands, response.getEntity());
    }

    @Test
    public void getBands_SQLException() throws Exception {
        // Mock the service layer to throw SQLException
        when(bandService.getAllBands()).thenThrow(new SQLException());

        // Call the method
        Response response = bandController.getBands();

        // Verify the result
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
