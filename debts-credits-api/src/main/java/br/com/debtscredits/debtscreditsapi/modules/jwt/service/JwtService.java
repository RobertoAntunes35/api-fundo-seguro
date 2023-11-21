package br.com.debtscredits.debtscreditsapi.modules.jwt.service;

import org.springframework.stereotype.Service;

import br.com.debtscredits.debtscreditsapi.config.exception.AuthException;
import br.com.debtscredits.debtscreditsapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static org.springframework.util.ObjectUtils.isEmpty;


import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token) {
        var acessToken = extractToken(token);
        try {
            var claims = Jwts
                        .parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                        .build()
                        .parseClaimsJws(acessToken)
                        .getBody();
                var user = JwtResponse.getUser(claims);
                if (isEmpty(user) || isEmpty(user.getId())) {
                    throw new AuthException("The user is not valid");
                }


        } catch (Exception ex){
            ex.printStackTrace();
            throw new AuthException("Error while trynt to process the acess token");
        }
    }

    private String extractToken(String token) {
        if(isEmpty(token)) {
            throw new AuthException("Authentication failed");
        }
        return token;
    }
}
