package com.example.lab6;

import com.example.lab6.token.*;
import com.example.lab6.visitor.CalcVisitor;
import com.example.lab6.visitor.ParserVisitor;
import com.example.lab6.visitor.PrintVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter expression:\n");

            String string = br.readLine();

            try {
                List<Token> infix = Tokenizer.parse(string);

                ParserVisitor parserVisitor = new ParserVisitor();
                PrintVisitor printVisitor = new PrintVisitor();
                CalcVisitor calcVisitor = new CalcVisitor();

                infix.forEach(token -> token.accept(parserVisitor));
                List<Token> rpr = parserVisitor.getResult();

                rpr.forEach(token -> token.accept(printVisitor));
            System.out.println(printVisitor.getResult());
                rpr.forEach(token -> token.accept(calcVisitor));

                System.out.println(calcVisitor.getResult());

            } catch (RuntimeException nfe) {
                System.err.println("Invalid Format!");
            }
        }
    }
}