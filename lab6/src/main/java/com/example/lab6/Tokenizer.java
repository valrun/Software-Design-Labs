package com.example.lab6;

import com.example.lab6.aspect.Profile;
import com.example.lab6.state.Start;
import com.example.lab6.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tokenizer {
    @Autowired
    Start start;

    @Profile
    public List<Token> parse(final String input) {
        return start.createToken(input);
    }

    public static String getName() {
        return "tokenizer";
    }
}
