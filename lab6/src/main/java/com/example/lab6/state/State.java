package com.example.lab6.state;

import com.example.lab6.token.Token;

import java.util.List;

public interface State {
    List<Token> createToken(String postfix);

    static String getName() {
        return "state";
    }
}
