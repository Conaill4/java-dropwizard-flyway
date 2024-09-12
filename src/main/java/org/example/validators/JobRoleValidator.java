package org.example.validators;

import org.example.models.JobRoleRequest;

public class JobRoleValidator {
public boolean validateJobRole(
        final JobRoleRequest jobRoleRequest) throws Exception {
       final int maxJobNameLength = 100;
       final int maxLocationLength = 100;
       final int maxJobDescLength = 100;
       final int maxJobResponsibilitiesLength = 100;
       if (jobRoleRequest.getRoleName().length() > maxJobNameLength) {
           throw new Exception("Role Name greater than 100 characters");
       }
       if (jobRoleRequest.getLocation().length() > maxLocationLength) {
           throw new Exception("Location greater than 200 characters");
       }
       if (jobRoleRequest.getDescription().length() > maxJobDescLength) {
           throw new Exception("Description greater than 500 characters");
       }
       if (jobRoleRequest.getResponsibilities()
               .length() > maxJobResponsibilitiesLength) {
           throw new Exception(
                   "Responsibilities greater than 200 characters");
       }
       if (jobRoleRequest.getNumberOfOpenPositions() < 1) {
           throw new Exception("Positions must be 1 or greater");
       }
       return true;
}
};
