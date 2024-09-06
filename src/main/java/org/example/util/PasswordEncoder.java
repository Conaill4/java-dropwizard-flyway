package org.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordEncoder {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER =
            new BCryptPasswordEncoder();
    private PasswordEncoder() {
    }
    public static String hashPassword(final String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    public static boolean verifyPassword(final String inputPassword,
                                         final String storedHash) {
        return PASSWORD_ENCODER.matches(inputPassword, storedHash);
    }
}
