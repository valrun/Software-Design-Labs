package com.example.lab6.state;

import com.example.lab6.token.NumberToken;
import com.example.lab6.token.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number implements State {
    @Override
    public List<Token> createToken(String postfix) {
        String[] strings = postfix.split("[ /()_+*:]", 2);
        String stringNumber = strings[0];
        int number = Integer.parseInt(strings[0]);
        postfix = postfix.substring(stringNumber.length());

        List<Token> result = new ArrayList<>();
        result.add(new NumberToken(number));
        result.addAll(new Start().createToken(postfix));

        return result;
    }
}
