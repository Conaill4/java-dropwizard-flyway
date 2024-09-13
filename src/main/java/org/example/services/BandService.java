package org.example.services;

import org.example.daos.BandDao;
import org.example.models.Band;

import java.sql.SQLException;
import java.util.List;

public class BandService {
    BandDao bandDao;


    public BandService(final BandDao bandDao) {
        this.bandDao = bandDao;
    }

    public List<Band> getAllBands() throws SQLException {
        return bandDao.getAllBands();
    }
}
