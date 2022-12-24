package com.example.lab6.visitor;

import com.example.lab6.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements Visitor {
    private final List<Token> reversePolishNotation = new ArrayList<>();
    private final Stack<Token> operation = new Stack<>();

    @Override
    public void visit(NumberToken token) {
        reversePolishNotation.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.getValue() == BraceType.LEFT) {
            operation.push(token);
            return;
        }
        while (operation.peek().getClass() != Brace.class) {
            reversePolishNotation.add(operation.pop());
        }
        operation.pop();
    }

    @Override
    public void visit(Operation token) {
        operation.push(token);
    }

    public List<Token> getResult() {
        while (!operation.isEmpty()) {
            reversePolishNotation.add(operation.pop());
        }
        return reversePolishNotation;
    }
}
