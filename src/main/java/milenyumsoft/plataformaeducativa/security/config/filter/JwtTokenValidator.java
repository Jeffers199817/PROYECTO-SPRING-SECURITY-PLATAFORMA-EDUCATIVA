package milenyumsoft.plataformaeducativa.security.config.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import milenyumsoft.plataformaeducativa.utils.JwtUtils;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {


    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NotNull  HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        //1.- Traer el token envio en la solicitud desde la cabecera
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        //2.- Verificar que el token existe
        if(jwtToken!=null){
            //en el encabezado antes del token viene la palabra bearer (esquema de autenticación)
            //por lo que debemos sacarlo
            jwtToken = jwtToken.substring(7);//son 7 letras + 1 espacio

            try {
                //Validamos el token
                DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
                //si token es valido extaemos la información del token

                String username = jwtUtils.extractUsername(decodedJWT);
                //EXTEAMOS LOS CLAIN SPECIFICO
                String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

                //transformamos los permisos en uns colección de grantedauthority interfaz de spring para representar ROles/permisos

                Collection authoritieslist = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                //Obtenermos el security contex actual del security context holder que es  el contenedor la información de authetnicacon den spring
                SecurityContext contex = SecurityContextHolder.getContext();

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authoritieslist);
                //Indicar que el usuario está autenticado
                contex.setAuthentication(authentication);

                SecurityContextHolder.setContext(contex);
            }catch (JWTVerificationException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
                return; // Terminamos aquí, no continuamos con la cadena de filtros
            }

        }
        filterChain.doFilter(request,response);
    }
}
