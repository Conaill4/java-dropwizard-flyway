package org.example;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.example.auth.JwtAuthenticator;
import org.example.auth.RoleAuthorisor;
import org.example.controllers.AuthController;
import org.example.controllers.BandController;
import org.example.controllers.CapabilityController;
import org.example.controllers.JobRoleController;
import org.example.controllers.TestController;
import org.example.daos.AuthDao;
import org.example.daos.BandDao;
import org.example.daos.CapabilityDao;
import org.example.daos.JobRoleDao;
import org.example.daos.TestDao;
import org.example.mappers.JobRoleMapper;
import org.example.models.JwtToken;
import org.example.services.AuthService;
import org.example.services.BandService;
import org.example.services.CapabilityService;
import org.example.services.JobRoleService;
import org.example.services.TestService;
import org.example.validators.OrderBySanitiser;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.example.validators.AuthValidator;
import org.example.validators.PaginationSanitiser;

import java.security.Key;

public class TestApplication extends Application<TestConfiguration> {
    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }
    @Override
    public String getName() {
        return "Test";
    }
    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final TestConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }
    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        Key jwtKey = Jwts.SIG.HS256.key().build();

        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<JwtToken>()
                        .setAuthenticator(new JwtAuthenticator(jwtKey))
                        .setAuthorizer(new RoleAuthorisor())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(
                new AuthValueFactoryProvider.Binder<>(JwtToken.class));

        environment.jersey().register(new TestController(
                new TestService(new TestDao())));
        environment.jersey().register((new JobRoleController(
               new JobRoleService(new JobRoleDao(), new JobRoleMapper()),
                new PaginationSanitiser(), new OrderBySanitiser()
                )));
        environment.jersey().register(new AuthController(
                new AuthService(new AuthDao(), jwtKey, new AuthValidator())));
        environment.jersey()
                .register(new BandController(
                        new BandService(
                                new BandDao()
                                )));
        environment.jersey()
                .register(new CapabilityController(
                        new CapabilityService(
                                new CapabilityDao())));
    }

}
