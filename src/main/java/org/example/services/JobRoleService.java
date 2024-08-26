package org.example.services;

import org.example.daos.DatabaseConnector;
import org.example.daos.JobRoleDao;

public class JobRoleService {
    private final JobRoleDao jobRoleDao;
    private final DatabaseConnector databaseConnector;

    public JobRoleService(final JobRoleDao jobRoleDao,
                          final DatabaseConnector databaseConnector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }
}
