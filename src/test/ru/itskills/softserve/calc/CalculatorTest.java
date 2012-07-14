/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itskills.softserve.calc;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author casper
 */
@RunWith(value = Parameterized.class)
public class CalculatorTest {
    private Calc c;
    
    public CalculatorTest(Calc c) {
        this.c = c;
    }
    
    @Parameterized.Parameters
    public static Collection parametres() {
        return Arrays.asList(new Calc[][] {
            {new Calculator()},
            {new CalculatorMock()}
        });
    }
    
    @Test
    public void testExecPlus()
            throws OperationNotSupportedException, OverflowException {
        long result = c.exec(1L, '+', 5L);
        assertEquals(6L, result);
    }
    
    @Test
    public void testExecMinus()
            throws OperationNotSupportedException, OverflowException {
        long result = c.exec(14L, '-', 8L);
        assertEquals(6L, result);
    }
    
    @Test
    public void testExecMul()
            throws OperationNotSupportedException, OverflowException {
        long result = c.exec(2L, '*', 3L);
        assertEquals(6L, result);
    }
    
    @Test
    public void testExecDiv()
            throws OperationNotSupportedException, OverflowException {
        long result = c.exec(12L, '/', 2L);
        assertEquals(6L, result);
    }
    
    @Test(expected=OperationNotSupportedException.class)
    public void testExecUnsupportedException()
            throws OperationNotSupportedException, OverflowException {
        c.exec(0L, '&', 0L);
    }
    
    @Test(expected=OverflowException.class)
    public void testExecOverflowException()
            throws OperationNotSupportedException, OverflowException {
        c.exec(Long.MAX_VALUE, '+', 1L);
    }
    
}
