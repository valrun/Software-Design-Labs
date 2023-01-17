package com.example.lab6.visitor;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.Brace;
import com.example.lab6.token.NumberToken;
import com.example.lab6.token.Operation;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class CalcVisitor implements Visitor {
    private final Stack<Double> cashe = new Stack<>();

    @Profile
    @Override
    public void visit(NumberToken token) {
        cashe.push((double) token.getValue());
    }

    @Profile
    @Override
    public void visit(Brace token) {
        throw new RuntimeException();
    }

    @Profile
    @Override
    public void visit(Operation token) {
        double num2 = cashe.pop();
        double num1 = cashe.pop();

        switch (token.getValue()) {
            case PLUS -> cashe.push(num1 + num2);
            case MINUS -> cashe.push(num1 - num2);
            case MUL -> cashe.push(num1 * num2);
            case DIV -> cashe.push(num1 / num2);
        }
    }

    @Profile
    @Override
    public Double getResult() {
        return cashe.pop();
    }

    public static String getName() {
        return "calcVisitor";
    }
}
