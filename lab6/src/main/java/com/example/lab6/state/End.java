package com.example.lab6.state;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.Token;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class End implements State {

    @Profile
    @Override
    public List<Token> createToken(String postfix) {
        return Collections.emptyList();
    }

    public static String getName() {
        return "end";
    }
}
