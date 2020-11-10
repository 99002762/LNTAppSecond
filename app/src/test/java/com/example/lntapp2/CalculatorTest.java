package com.example.lntapp2;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

    public void testAdd(){
        int expected = 30;
        int actual = Calculator.add(10,20);
        assertEquals(expected,actual);
    }

}