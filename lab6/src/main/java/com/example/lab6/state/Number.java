package com.example.lab6.state;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.NumberToken;
import com.example.lab6.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Number implements State {
    @Autowired
    private Start start;

    @Profile
    @Override
    public List<Token> createToken(String postfix) {
        String[] strings = postfix.split("[ /()_+*:-]", 2);
        String stringNumber = strings[0];
        int number = Integer.parseInt(strings[0]);
        postfix = postfix.substring(stringNumber.length());

        List<Token> result = new ArrayList<>();
        result.add(new NumberToken(number));
        result.addAll(start.createToken(postfix));

        return result;
    }

    public static String getName() {
        return "number";
    }
}
