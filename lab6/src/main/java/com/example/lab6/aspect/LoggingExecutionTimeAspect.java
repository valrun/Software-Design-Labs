package com.example.lab6.aspect;

import com.example.lab6.ProfileConfig;
import com.example.lab6.Tokenizer;
import com.example.lab6.state.End;
import com.example.lab6.state.Error;
import com.example.lab6.state.Number;
import com.example.lab6.state.Start;
import com.example.lab6.visitor.CalcVisitor;
import com.example.lab6.visitor.ParserVisitor;
import com.example.lab6.visitor.PrintVisitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingExecutionTimeAspect {
    int prefix = 0;
    Map<String, Integer> counter = new HashMap<>();
    Map<String, Long> allTime = new HashMap<>();

    public Object logExecutionTime(ProceedingJoinPoint joinPoint, String name) throws Throwable {
        long startNs = System.nanoTime();
        if (ProfileConfig.timeLogs.contains(name)) {
            System.err.println(" ".repeat(prefix) + "Start method " + joinPoint.getSignature().getName()
                    + " of state " + name);
            prefix++;
        }

        Object result = joinPoint.proceed(joinPoint.getArgs());

        long time = System.nanoTime() - startNs;
        if (ProfileConfig.timeLogs.contains(name)) {
            prefix--;
            System.err.println(" ".repeat(prefix) + "Finish method " + joinPoint.getSignature().getName()
                    + ", execution time in ns: " + time);
        }


        if (ProfileConfig.timeStat.contains(name)) {
            if (!counter.containsKey(name)) {
                counter.put(name, 0);
                allTime.put(name, 0L);
            }
            counter.put(name, counter.get(name) + 1);
            allTime.put(name, allTime.get(name) + time);
        }

        return result;
    }

    @Around("execution(* com.example.lab6.Tokenizer.parse(..))")
    public Object logExecutionTimeTokenizer(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, Tokenizer.getName());
    }

    @Around("execution(* com.example.lab6.state.End.*(..))")
    public Object logExecutionTimeEnd(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, End.getName());
    }

    @Around("execution(* com.example.lab6.state.Error.*(..))")
    public Object logExecutionTimeError(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, Error.getName());
    }

    @Around("execution(* com.example.lab6.state.Number.*(..))")
    public Object logExecutionTimeNumber(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, Number.getName());
    }

    @Around("execution(* com.example.lab6.state.Start.*(..))")
    public Object logExecutionTimeStart(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, Start.getName());
    }

    @Around("execution(* com.example.lab6.visitor.ParserVisitor.*(..))")
    public Object logExecutionTimeParserVisitor(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, ParserVisitor.getName());
    }

    @Around("execution(* com.example.lab6.visitor.PrintVisitor.*(..))")
    public Object logExecutionTimePrintVisitor(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, PrintVisitor.getName());
    }

    @Around("execution(* com.example.lab6.visitor.CalcVisitor.*(..))")
    public Object logExecutionTimeCalcVisitor(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint, CalcVisitor.getName());
    }

    @AfterReturning("execution(* com.example.lab6.visitor.CalcVisitor.getResult(..))")
    public void stat() {
        if (!ProfileConfig.timeStat.isEmpty()) {
            System.err.println("\nHow many times called:");
            counter.forEach((name, time) -> System.err.println(name + ":\t" + time));

            System.err.println("\nCommon time in ns:");
            allTime.forEach((name, time) -> System.err.println(name + ":\t" + time));

            System.err.println("\nAverage time in ns:");
            allTime.forEach((name, time) -> System.err.println(name + ":\t" + (time / counter.get(name))));
        }
    }
}
