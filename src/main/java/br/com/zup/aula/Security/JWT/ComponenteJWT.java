package br.com.zup.aula.Security.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class ComponenteJWT {

    @Value("${jwt.segredo}")
    private String segredo;

    @Value("${jwt.milissegundos}")
    private Long milissegundos;

    public String gerarToken(String username){
        Date vencimento = new Date(System.currentTimeMillis()+milissegundos);

        String token = Jwts.builder().setSubject(username)
                .setExpiration(vencimento)
                .signWith(SignatureAlgorithm.HS512, segredo.getBytes()).compact();

        return token;
    }
}
