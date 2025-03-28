package milenyumsoft.plataformaeducativa.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {


    @Value("${security.jwt.private.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;


    //1. - metodo para crear una funcion createToken amos a necesitar nuesta calve secreta y el algoritmo

    public String createToken(Authentication authentication){


        //2.- Creamos un objeto de tipo algorithm
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        //3.- Traemos a nuesto usuario que está dentro de security context holder

        String username = authentication.getPrincipal().toString();

        //4.- Traemos los permisos separados por coma
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        //5.- Generamos el token apartir de lo demas

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwtToken;

    }

    // 6 . Método para decodificar jwt

    public DecodedJWT validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            //SI ESTÁ TODO OK, no genera excepciones y hace el return
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;

        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Invalid token . Not authorized.");

        }
    }

    public String extractUsername( DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
