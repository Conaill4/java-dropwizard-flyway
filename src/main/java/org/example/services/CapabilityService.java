package org.example.services;

import org.example.daos.CapabilityDao;
import org.example.models.Capability;

import java.sql.SQLException;
import java.util.List;

public class CapabilityService {
    CapabilityDao capabilityDao;

    public CapabilityService(final CapabilityDao capabilityDao) {
        this.capabilityDao = capabilityDao;
    }

    public List<Capability> getAllCapabilities() throws SQLException {
        return capabilityDao.getAllCapabilities();
    }
}
