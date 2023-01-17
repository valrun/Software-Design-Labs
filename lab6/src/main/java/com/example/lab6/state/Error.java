package com.example.lab6.state;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.Token;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Error implements State {

    @Profile
    @Override
    public List<Token> createToken(String postfix) {
        throw new RuntimeException();
    }

    public static String getName() {
        return "error";
    }
}
