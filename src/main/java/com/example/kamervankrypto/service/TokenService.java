package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.TokenSecurityKey;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.Token.TokenDAO;
import com.example.kamervankrypto.repository.Trader.TraderDAO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    private TokenDAO tokenDAO;
    private TraderDAO traderDAO;
    private final int EXPIRATIONTIMEMILLIS = 10 * 60 * 1000;  // 10 minutes in milliseconds

    @Autowired
    public TokenService(TokenDAO tokenDAO, TraderDAO traderDAO) {
        this.tokenDAO = tokenDAO;
        this.traderDAO = traderDAO;
    }

    public String createToken(Trader trader) {
        // This JWT is signed with a secret key. THis secret key is made when not available. See TokenSecurityKey.class

        Date currentDate = new Date(System.currentTimeMillis());
        Date validDate = new Date(System.currentTimeMillis() + EXPIRATIONTIMEMILLIS);

        String token = Jwts.builder()
                .setIssuer("Kamer van Krypto Security Authenticator")  // this is dummy name for the issuer.
                .setSubject(String.valueOf(trader.getID()))  // to which user is the Token issued. TODO: find out which data should be here, and why. --> KAN OOK IDTOKEN ZIJN!
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentDate) // Date in milliseconds at moment is making the Token. This value must be numeric (so no LocalDateTime)!
                .setExpiration(validDate) // Time at which the token expires, also numeric value.
                .signWith(TokenSecurityKey.getSecretKey())  // Secret key, normally saved a secured file, not for this excersize.
                .compact();  // create Token with input above

        saveToken(trader, token);

        return token;

    }

    public void saveToken(Trader trader, String token) {
        tokenDAO.saveToken(trader.getID(), token);
    }

    public Trader checkTokenValidity(String token) {

        Jws<Claims> jws = null;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(TokenSecurityKey.getSecretKey())
                    .build()
                    .parseClaimsJws(token);

            // we can safely trust the JWT
            // System.out.println(jws.toString());

        } catch (JwtException ex) {
            System.out.println("Token niet correct of niet valide"); // TODO: juiste response geven!

            // we *cannot* use the JWT as intended by its creator
        }

        //TODO: Find out how to extract data from jws<Claims> to Map or something
        //TODO: verify whether data matches Trader info
        //DONE: return trader
        //TODO: transform every call to add a URL-variable with the token to get a Trader with this verrification
        //TODO: What if things don't work or check out? What codes to send back.

        int traderID = tokenDAO.getTraderIDFromToken(token);
        return traderDAO.findById(traderID);

    }


}
