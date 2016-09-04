package org.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.kit.DateKit;
import org.javachina.kit.Utils;
import org.javachina.model.Token;
import org.javachina.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public Token create(Integer uid, String scope) {

        Token token = Token.db.eq("uid", uid).eq("scope", scope)
                .gt("expired_time", DateKit.getCurrentUnixTime())
                .orderBy("id desc").first(Token.class);

        if(null != token){
            token.id = null;
            return token;
        }

        String acess_token = Utils.createToken(uid);

        token = new Token();
        token.acess_token = acess_token;
        token.uid = uid;
        token.scope = scope;
        token.create_time = DateKit.getCurrentUnixTime();
        token.expired_time = DateKit.getCurrentUnixTime() + 3600;

        Token.db.insert(token);

        token.id = null;
        return token;
    }

    @Override
    public Token getToken(String token) {
        return Token.db.eq("acess_token", token).first(Token.class);
    }
}
