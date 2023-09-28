package org.example;

import org.example.calculator.Calculator;
import org.example.model.*;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        SessionData data = GenerateTestData.generate();
        Calculator calculator = new Calculator();
        calculator.calculate(data);


// https://www.dariawan.com/tutorials/java/java-instant-tutorial-examples/

    }
}


