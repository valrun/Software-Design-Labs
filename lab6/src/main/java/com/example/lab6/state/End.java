package com.example.lab6.state;

import com.example.lab6.token.Token;

import java.util.Collections;
import java.util.List;

public class End implements State {
    @Override
    public List<Token> createToken(String postfix) {
        return Collections.emptyList();
    }
}
