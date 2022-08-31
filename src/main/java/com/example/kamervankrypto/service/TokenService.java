package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.TokenSecurityKey;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.TokenDAO;
import com.example.kamervankrypto.repository.TraderDAO;
import io.jsonwebtoken.*;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    private TokenDAO tokenDAO;
    private TraderDAO traderDAO;

    private final int EXPIRATIONTIMEMILLIS = 1 * 60 * 1000;  // 10 minutes in milliseconds

//    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
//    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


    @Autowired
    public TokenService(TokenDAO tokenDAO, TraderDAO traderDAO) {
        this.tokenDAO = tokenDAO;
        this.traderDAO = traderDAO;
    }

    public String createToken(Trader trader) {
        // This JWT is signed with a secret key. THis secret key is made when not available. See TokenSecurityKey.class

//

        return Jwts.builder()
                .claim("name", trader.getFirstName() + " " + trader.getPrefix() + " " + trader.getName())
                .claim("email", trader.getEmail())
                .setIssuer("Kamer van Krypto Security Authenticator")  // this is dummy name for the issuer.
                .setSubject(String.valueOf(trader.getID()))  // to which user is the Token issued. TODO: find out which data should be here, and why. --> KAN OOK IDTOKEN ZIJN!
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(System.currentTimeMillis())) // Date in milliseconds at moment is making the Token. This value must be numeric (so no LocalDateTime)!
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIMEMILLIS)) // Time at which the token expires, also numeric value.
                .signWith(TokenSecurityKey.getSecretKey())  // Secret key, normally saved a secured file, not for this excersize.
                .compact();  // create Token with input above

    }

    public void saveToken(Trader trader, String token) {
        tokenDAO.saveToken(trader.getID(), token);
    }

    public Trader checkTokenValidity(String token) {

        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(TokenSecurityKey.getSecretKey())
                    .build()
                    .parseClaimsJws(token);

            // we can safely trust the JWT

            System.out.println(jws.toString());

        } catch (JwtException ex) {
            System.out.println("Token niet correct");

            // we *cannot* use the JWT as intended by its creator
        }

        //TODO: Find out how to extract data from jws<Claims> to Map or something
        //TODO: verify whether data matches Trader info
        //TODO: return trader
        //TODO: transform every call to add a URL-variable with the token to get a Trader with this verrification
        //TODO: What if things don't work or check out? What codes to send back.


        if (tokenDAO.checkTokenValidity(token)) {
            int traderID = tokenDAO.getTraderIDFromToken(token);
            return traderDAO.findById(traderID);
        } else {
            //TODO: Wat te doen als validity niet goed is?
            return null;
        }

    }


}
