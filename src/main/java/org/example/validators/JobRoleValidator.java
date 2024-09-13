package org.example.validators;

import org.example.models.JobRoleRequest;
public class JobRoleValidator {
    private static final int MAX_JOBNAME_LENGTH = 100;
    private static final int MAX_LOCATION_LENGTH = 200;
    private static final int MAX_JOBDESC_LENGTH = 500;
    private static final int MAX_JOBRESPONSIBILLITY_LENGTH = 200;
public boolean validateJobRole(
        final JobRoleRequest jobRoleRequest) throws Exception {
       if (jobRoleRequest.getRoleName().length() > MAX_JOBNAME_LENGTH) {
           throw new Exception("Role Name greater than "
                   + MAX_JOBNAME_LENGTH + "characters");
       }
       if (jobRoleRequest.getLocation().length() > MAX_LOCATION_LENGTH) {
           throw new Exception("Location greater than "
                   + MAX_LOCATION_LENGTH + "characters");
       }
       if (jobRoleRequest.getDescription().length() > MAX_JOBDESC_LENGTH) {
           throw new Exception("Description greater than "
                   + MAX_JOBDESC_LENGTH + "characters");
       }
       if (jobRoleRequest.getResponsibilities()
               .length() > MAX_JOBRESPONSIBILLITY_LENGTH) {
           throw new Exception(
                   "Responsibilities greater than "
                           + MAX_JOBRESPONSIBILLITY_LENGTH + "characters");
       }
       if (jobRoleRequest.getNumberOfOpenPositions() < 1) {
           throw new Exception("Positions must be 1 or greater ");
       }
       return true;
}
};
