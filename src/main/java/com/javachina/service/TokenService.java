package com.javachina.service;

import com.javachina.model.Token;

public interface TokenService {

    Token create(Integer uid, String scope);

    Token getToken(String token);

}
