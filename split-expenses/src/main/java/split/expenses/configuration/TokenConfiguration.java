package split.expenses.configuration;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class TokenConfiguration {
    public String generateJWT(){
        Set<String> roles = new HashSet<>(
                Arrays.asList("USER")
        );

        return Jwt.issuer("split-expenses")
                .subject("split-expenses")
                .groups(roles)
                .expiresAt(
                        System.currentTimeMillis() + 9999999
                )
                .sign();
    }
}
