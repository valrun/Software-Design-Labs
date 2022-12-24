package com.example.lab6;

import com.example.lab6.state.Start;
import com.example.lab6.token.Token;

import java.util.List;

public class Tokenizer {
    public static List<Token> parse(final String input) {
        return new Start().createToken(input);
    }
}
