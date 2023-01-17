package com.example.lab6.visitor;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.example.lab6.token.OperationType.*;

@Component
public class ParserVisitor implements Visitor {
    private List<Token> reversePolishNotation = new ArrayList<>();
    private Stack<Token> operation = new Stack<>();

    @Profile
    @Override
    public void visit(NumberToken token) {
        reversePolishNotation.add(token);
    }

    @Profile
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

    @Profile
    @Override
    public void visit(Operation token) {
        while (!operation.isEmpty() &&
                operation.peek().getClass() != Brace.class) {
            Operation top = (Operation) operation.peek();
            if ((token.getValue() == PLUS || token.getValue() == MINUS) &&
                    (top.getValue() == MUL || top.getValue() == DIV)) {
                reversePolishNotation.add(operation.pop());
            } else {
                break;
            }
        }
        operation.push(token);
    }

    @Profile
    @Override
    public List<Token> getResult() {
        while (!operation.isEmpty()) {
            reversePolishNotation.add(operation.pop());
        }

        List<Token> result = reversePolishNotation;
        reversePolishNotation = new ArrayList<>();
        operation = new Stack<>();

        return result;
    }

    public static String getName() {
        return "parserVisitor";
    }
}
