package com.example.lab6.state;

import com.example.lab6.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Start implements State {
    private final Map<Character, Token> convertMap = Map.of(
            '+', new Operation(OperationType.PLUS),
            '-', new Operation(OperationType.MINUS),
            '*', new Operation(OperationType.MUL),
            '/', new Operation(OperationType.DIV),
            '%', new Operation(OperationType.DIV),
            '(', new Brace(BraceType.LEFT),
            ')', new Brace(BraceType.RIGHT)
    );

    @Override
    public List<Token> createToken(String postfix) {
        postfix = postfix.trim();
        if (postfix.length() == 0) {
            return new End().createToken(postfix);
        }

        switch (postfix.charAt(0)) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                return new Number().createToken(postfix);
            }
            case '+', '-', '*', '/', ':', '(', ')' -> {
                Token token = convertMap.get(postfix.charAt(0));

                List<Token> result = new ArrayList<>();
                result.add(token);
                result.addAll(new Start().createToken(postfix.substring(1)));

                return result;
            }
        }
        return new Error().createToken(postfix);
    }
}
