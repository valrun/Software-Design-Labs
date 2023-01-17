package com.example.lab6.state;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Start implements State {
    @Autowired
    private End end;

    @Autowired
    private Error error;

    @Autowired
    private Number number;

    @Profile
    @Override
    public List<Token> createToken(String postfix) {
        postfix = postfix.trim();
        if (postfix.length() == 0) {
            return end.createToken(postfix);
        }

        switch (postfix.charAt(0)) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                return number.createToken(postfix);
            }
            case '+', '-', '*', '/', ':', '(', ')' -> {
                Token token = convertMap.get(postfix.charAt(0));

                List<Token> result = new ArrayList<>();
                result.add(token);
                result.addAll(this.createToken(postfix.substring(1)));

                return result;
            }
        }
        return error.createToken(postfix);
    }

    public static String getName() {
        return "start";
    }

    private final Map<Character, Token> convertMap = Map.of(
            '+', new Operation(OperationType.PLUS),
            '-', new Operation(OperationType.MINUS),
            '*', new Operation(OperationType.MUL),
            '/', new Operation(OperationType.DIV),
            '%', new Operation(OperationType.DIV),
            '(', new Brace(BraceType.LEFT),
            ')', new Brace(BraceType.RIGHT)
    );
}
