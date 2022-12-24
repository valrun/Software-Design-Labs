package com.example.lab6.visitor;

import com.example.lab6.token.*;

public interface Visitor {
    void visit(NumberToken token);
    void visit(Brace token);
    void visit(Operation token);
}
