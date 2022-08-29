package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.TokenDAO;
import com.example.kamervankrypto.repository.TraderDAO;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenService {

    private TokenDAO tokenDAO;
    private TraderDAO traderDAO;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


    @Autowired
    public TokenService(TokenDAO tokenDAO, TraderDAO traderDAO) {
        this.tokenDAO = tokenDAO;
        this.traderDAO = traderDAO;
    }

    public String createToken(){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void saveToken(Trader trader, String token){
        tokenDAO.saveToken(trader.getID(), token);
    }

    public Trader checkTokenValidity(String token){
        if (tokenDAO.checkTokenValidity(token)){
            int traderID = tokenDAO.getTraderIDFromToken(token);
            return traderDAO.findById(traderID);
        } else{
            //TODO: Wat te doen als validity niet goed is?
            return null;
        }

    }



}
