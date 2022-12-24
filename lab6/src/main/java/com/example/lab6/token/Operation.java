package com.example.lab6.token;

import com.example.lab6.visitor.Visitor;

public class Operation implements Token {

    private final OperationType value;

    public Operation(OperationType value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public OperationType getValue() {
        return value;
    }
}

