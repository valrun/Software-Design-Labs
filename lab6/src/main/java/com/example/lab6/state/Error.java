package com.example.lab6.state;

import com.example.lab6.token.Token;

import java.util.List;

public class Error implements State {
    @Override
    public List<Token> createToken(String postfix) {
        throw new RuntimeException();
    }
}
