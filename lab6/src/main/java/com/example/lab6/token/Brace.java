package com.example.lab6.token;

import com.example.lab6.visitor.Visitor;

public class Brace implements Token {

    private final BraceType value;

    public Brace(BraceType value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public BraceType getValue() {
        return value;
    }
}

;
