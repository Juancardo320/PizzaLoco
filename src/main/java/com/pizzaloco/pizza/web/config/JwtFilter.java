package com.pizzaloco.pizza.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //1. Valida que sea un header valido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //obtener el header
        //validar que el encabezado sea valido
        if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")){ //si es nulo no me sirve que la cadena siga su trabajo
            filterChain.doFilter(request,response);
            return;
        }
        //2. validar que el jwt sea valido
        String jwt = authHeader.split(" ")[1].trim();//capturar el jwt que llega en nuestro authHeader

        if(!this.jwtUtils.isValid(jwt)){
            filterChain.doFilter(request,response);
            return;
        }//validar el jwt

        //3. Cargar el usuario del UserDetalisService
        String username = this.jwtUtils.getUsername(jwt);//obtener el usernae de nuestro jwt y buscarlo en nuestro repositorio de usuarios

        User user = (User) this.userDetailsService.loadUserByUsername(username);

        //4, cargar al usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        ); //usamos el constructor con 3 parametros para autenticar ya que no tnemos pass

        //cargar esta autenticacion al contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
