package com.example.lab6.visitor;

import com.example.lab6.aspect.Profile;
import com.example.lab6.token.Brace;
import com.example.lab6.token.NumberToken;
import com.example.lab6.token.Operation;
import org.springframework.stereotype.Component;


@Component
public class PrintVisitor implements Visitor {
    private StringBuilder result = new StringBuilder();

    @Profile
    @Override
    public void visit(NumberToken token) {
        result.append("NUMBER(")
                .append(token.getValue())
                .append(") ");
    }

    @Profile
    @Override
    public void visit(Brace token) {
        result.append(token.getValue()).append(" ");
    }

    @Profile
    @Override
    public void visit(Operation token) {
        result.append(token.getValue()).append(" ");
    }

    @Profile
    @Override
    public String getResult() {
        String res = result.toString();
        result = new StringBuilder();
        return res;
    }

    public static String getName() {
        return "printVisitor";
    }
}
