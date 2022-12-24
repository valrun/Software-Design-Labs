package com.example.lab6.token;

import com.example.lab6.visitor.Visitor;

public class NumberToken implements Token {
    private final int value;

    public NumberToken(int value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getValue() {
        return value;
    }
}
