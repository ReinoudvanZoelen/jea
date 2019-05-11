package services;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import entities.Player;

@ApplicationScoped
public class JwtService {
    private static final String SECRET = "superdupersecret";
    private static final int TOKEN_VALIDITY = 14400;
    private static final String ISSUER = "webappjeaapi";
    private static final String AUDIENCE = "webappjea";

    public String createJwt(final Player player) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            var iat = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(iat);
            calendar.add(Calendar.SECOND, TOKEN_VALIDITY);
            var exp = calendar.getTime();

            String token = JWT.create().withSubject(player.getEmailAddress()).withIssuer(ISSUER).withAudience(AUDIENCE)
                    .withIssuedAt(iat).withExpiresAt(exp).withClaim("role", player.getRole())
                    .withClaim("name", player.getFullName()).sign(algorithm);

            return token;
        } catch (JWTCreationException | IllegalArgumentException | UnsupportedEncodingException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
        }

        return "";
    }
}
