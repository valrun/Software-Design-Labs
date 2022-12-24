package com.example.lab6.token;

import com.example.lab6.visitor.Visitor;

public interface Token {
    void accept(Visitor visitor);
}
