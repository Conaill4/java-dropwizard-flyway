//package org.example.validators;
//
//import org.example.exceptions.EmailException;
//import org.example.exceptions.Entity;
//import org.example.exceptions.PasswordException;
//import org.example.models.JobRoleResponse;
//import org.example.models.LoginRequest;
//
//import java.sql.SQLException;
//import java.util.Date;
//
//public class PaginationValidator {
//    public boolean isPaginationValid(final JobRoleResponse jobRoleResponse) throws
//            SQLException {
//        int jobRoleId = jobRoleResponse.getJobRoleId();
//        String roleName = jobRoleResponse.getRoleName();
//        String location = jobRoleResponse.getLocation();
//        String capabilityName = jobRoleResponse.getCapabilityName();
//        String bandName = jobRoleResponse.getBandName();
//        Date closingDate = jobRoleResponse.getClosingDate();
//
//        if (jobRoleId < 1) {
//            throw new SQLException();
//        }
//
//        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]"
//                + "+\\.[A-Za-z]{2,}$")) {
//            throw new EmailException(Entity.EMAIL,
//                    " is invalid.");
//        }
//        if (password == "" || password == null) {
//            throw new PasswordException(Entity.PASSWORD, " is empty.");
//        }
//        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)"
//                + "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,}$")) {
//            throw new PasswordException(Entity.PASSWORD,
//                    " is invalid.");
//        }
//        return true;
//    }
//    }
//}
