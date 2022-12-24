package com.example.lab6.visitor;

import com.example.lab6.token.Brace;
import com.example.lab6.token.NumberToken;
import com.example.lab6.token.Operation;

public class PrintVisitor implements Visitor {
    private final StringBuilder result = new StringBuilder();

    @Override
    public void visit(NumberToken token) {
        result.append("NUMBER(")
                .append(token.getValue())
                .append(") ");
    }

    @Override
    public void visit(Brace token) {
        result.append(token.getValue()).append(" ");
    }

    @Override
    public void visit(Operation token) {
        result.append(token.getValue()).append(" ");
    }

    public String getResult() {
        return result.toString();
    }
}
