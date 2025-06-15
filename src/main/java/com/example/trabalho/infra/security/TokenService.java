package com.example.trabalho.infra.security;

import com.example.trabalho.model.Pessoa;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.JWT;


@Service
public class TokenService {
    public String generateToken(Pessoa user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("sei nao?");
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            System.out.println("Generated Token: " + token); // Log para verificar o token
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("sei nao?");
            return JWT.require(algorithm)
                    .withIssuer("auth-api")  // Certifique-se de que o issuer é o mesmo
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";  // Retorna uma string vazia em caso de erro
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}