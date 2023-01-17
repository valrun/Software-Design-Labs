package com.example.lab6;

import com.example.lab6.state.End;
import com.example.lab6.state.Number;
import com.example.lab6.state.Error;
import com.example.lab6.state.Start;
import com.example.lab6.visitor.CalcVisitor;
import com.example.lab6.visitor.ParserVisitor;
import com.example.lab6.visitor.PrintVisitor;

import java.util.Set;

public class ProfileConfig {
    public static Set<String> timeLogs = Set.of(
            Tokenizer.getName(),
            End.getName(), Error.getName(), Number.getName(), Start.getName()
//            PrintVisitor.getName(), ParserVisitor.getName(), CalcVisitor.getName()
    );

    public static Set<String> timeStat = Set.of(
//            Tokenizer.getName(),
//            End.getName(), Error.getName(), Number.getName(), Start.getName(),
            PrintVisitor.getName(), ParserVisitor.getName(), CalcVisitor.getName()
    );
}
