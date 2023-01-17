package com.example.lab6;

import com.example.lab6.state.*;
import com.example.lab6.state.Error;
import com.example.lab6.state.Number;
import com.example.lab6.visitor.CalcVisitor;
import com.example.lab6.visitor.ParserVisitor;
import com.example.lab6.visitor.PrintVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.example.lab6.aspect.LoggingExecutionTimeAspect;
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ContextConfiguration {

    @Bean
    public LoggingExecutionTimeAspect aspect() {
        return new LoggingExecutionTimeAspect();
    }

    @Bean
    public CalcVisitor calcVisitor() {
        return new CalcVisitor();
    }

    @Bean
    public ParserVisitor parserVisitor() {
        return new ParserVisitor();
    }

    @Bean
    public PrintVisitor printVisitor() {
        return new PrintVisitor();
    }

    @Bean
    public Tokenizer tokenizer() { return new Tokenizer(); }

    @Bean
    public End end() { return new End(); }

    @Bean
    public Error error() { return new Error(); }

    @Bean
    public Number number() { return new Number(); }

    @Bean
    public Start start() { return new Start(); }

}
