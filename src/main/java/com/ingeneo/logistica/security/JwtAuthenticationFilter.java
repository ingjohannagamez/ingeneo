package com.ingeneo.logistica.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingeneo.logistica.api.dto.LoginDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        //setFilterProcessesUrl("/api/authenticate");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // Reading username and password from JSON body
            LoginDTO credentials = objectMapper.readValue(request.getInputStream(), LoginDTO.class);
            String username = credentials.getUsername();
            String password = credentials.getPassword();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing the user login credentials", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Logger log = LoggerFactory.getLogger(this.getClass());

        try {
            log.info("Inicio de la autenticación exitosa");
            
            // Generación del token JWT.
            String token = jwtTokenProvider.generateToken(authResult);
            log.info("Token JWT generado con éxito");

            // Agregando el token a la cabecera de la respuesta.
            response.addHeader("Authorization", "Bearer " + token);
            log.info("Token JWT añadido a la cabecera de la respuesta");

            // Continuar con la ejecución de la cadena de filtros (si es necesario).
            //super.successfulAuthentication(request, response, chain, authResult);
            log.info("Fin de la autenticación exitosa");
            
            log.info("Detalles del usuario autenticado: {}", authResult);
            log.info("Cabeceras de respuesta después de añadir el token: {}", response.getHeaderNames());

        } catch (Exception e) {
            log.error("Error durante la autenticación exitosa", e);
            // Opcional: puedes elegir manejar el error de manera específica o re-lanzarlo
            throw e;
        }
    }

}
