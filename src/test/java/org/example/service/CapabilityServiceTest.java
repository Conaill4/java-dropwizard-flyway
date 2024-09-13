package org.example.service;

import org.checkerframework.checker.units.qual.C;
import org.example.controllers.BandController;
import org.example.daos.BandDao;
import org.example.daos.CapabilityDao;
import org.example.models.Band;
import org.example.models.Capability;
import org.example.services.BandService;
import org.example.services.CapabilityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CapabilityServiceTest {
    CapabilityDao capabilityDao = Mockito.mock(CapabilityDao.class);
    CapabilityService capabilityService = new CapabilityService(capabilityDao);

    Capability capability1 = new Capability(1, "capability1");
    Capability capability2 = new Capability(2, "capability2");

    @Test
    public void getAllCapabilities_shouldReturnCapabilities()
            throws SQLException {
        List<Capability> capabilityList = Arrays.asList(capability1,capability2);
        Mockito.when(capabilityDao.getAllCapabilities()).thenReturn(capabilityList);
        List<Capability> response = capabilityService.getAllCapabilities();

        assertEquals(capabilityList, response);
    }

    @Test
    public void getAllCapabilities_shouldThrowSQLException()
            throws SQLException {

        Mockito.when(capabilityDao.getAllCapabilities()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> capabilityService.getAllCapabilities());
    }
}
