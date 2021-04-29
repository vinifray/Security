package br.com.zup.aula.Security.JWT;

import br.com.zup.aula.Security.exceptions.TokenNotValidException;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class FiltroDeAutolizacao extends BasicAuthenticationFilter {

    private ComponenteJWT componenteJWT;
    private UserDetailsService userDetailsService;

    public FiltroDeAutolizacao(AuthenticationManager authenticationManager, ComponenteJWT componenteJWT,
                               UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.componenteJWT = componenteJWT;
        this.userDetailsService = userDetailsService;
    }

    private UsernamePasswordAuthenticationToken pegarAutenticacao(HttpServletRequest request, String token){
            if(!componenteJWT.isTokenValid(token)){
                throw new TokenNotValidException("If quebrou");
            }
            Claims claims = componenteJWT.getClaims(token);
            UserDetails usuario = userDetailsService.loadUserByUsername(claims.getSubject());

            return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String autorizacao = request.getHeader("Authorization");

        if(autorizacao != null && autorizacao.startsWith("Token ")){
            try{
                UsernamePasswordAuthenticationToken auth = pegarAutenticacao(request, autorizacao.substring(6));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch (TokenNotValidException error){
                System.out.println(error.getMessage());
            }
        }
        chain.doFilter(request, response);
    }
}
