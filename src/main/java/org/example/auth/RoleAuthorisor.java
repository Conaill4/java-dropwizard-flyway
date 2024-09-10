package org.example.auth;

import io.dropwizard.auth.Authorizer;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;
import org.example.models.JwtToken;

import javax.ws.rs.container.ContainerRequestContext;

public class RoleAuthorisor implements Authorizer<JwtToken> {
    @Override
    public boolean authorize(final JwtToken jwtToken, final String s) {
        return jwtToken.getUserRole().getRoleName().equals(s);
    }

    @Override
    public boolean authorize(final JwtToken principle, final String role,
                             @Nullable
                             final ContainerRequestContext requestContext) {
        return principle.getUserRole().getRoleName().equals(role);
    }
}
