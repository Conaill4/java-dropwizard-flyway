package org.example.service;

import org.example.daos.AuthDao;
import org.example.daos.BandDao;
import org.example.models.Band;
import org.example.services.BandService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandServiceTest {

    BandDao bandDao = Mockito.mock(BandDao.class);
    BandService bandService = new BandService(bandDao);

    Band band1 = new Band(1, "band1");
    Band band2 = new Band(2, "band2");

    @Test
    public void getAllBands_shouldReturnBands()
            throws SQLException {
        List<Band> bandList = Arrays.asList(band1,band2);
        Mockito.when(bandDao.getAllBands()).thenReturn(bandList);
        List<Band> response = bandService.getAllBands();

        assertEquals(bandList, response);
    }

    @Test
    public void getAllBands_shouldThrowSQLException()
            throws SQLException {

        Mockito.when(bandDao.getAllBands()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> bandService.getAllBands());
    }
}
