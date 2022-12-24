package com.example.lab6.state;

import com.example.lab6.token.Token;

import java.util.List;

public interface State {
    public List<Token> createToken(String postfix);
}
