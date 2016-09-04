package org.javachina.service;

import org.javachina.model.Token;

public interface TokenService {

    Token create(Integer uid, String scope);

    Token getToken(String token);

}
