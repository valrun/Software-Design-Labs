package com.example.lab6;

import com.example.lab6.state.State;
import com.example.lab6.token.*;
import com.example.lab6.visitor.CalcVisitor;
import com.example.lab6.visitor.ParserVisitor;
import com.example.lab6.visitor.PrintVisitor;
import com.example.lab6.visitor.Visitor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        Visitor parserVisitor = (Visitor) ctx.getBean(ParserVisitor.getName());
        Visitor printVisitor = (Visitor) ctx.getBean(PrintVisitor.getName());
        Visitor calcVisitor = (Visitor) ctx.getBean(CalcVisitor.getName());

        Tokenizer tokenizer = ctx.getBean(Tokenizer.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter expression or 'exit':\n");

            String string = br.readLine();
            if (Objects.equals(string, "exit")) {
                break;
            }

            try {
                List<Token> infix = tokenizer.parse(string);

                infix.forEach(token -> token.accept(parserVisitor));
                List<Token> rpr = (List<Token>) parserVisitor.getResult();

                rpr.forEach(token -> token.accept(printVisitor));
                System.out.println(printVisitor.getResult());
                rpr.forEach(token -> token.accept(calcVisitor));

                System.out.println(calcVisitor.getResult());

            } catch (RuntimeException nfe) {
                System.out.println("Invalid Format!");
//                nfe.printStackTrace();
            }
        }
    }
}