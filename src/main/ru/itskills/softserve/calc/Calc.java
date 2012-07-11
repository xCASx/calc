/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itskills.softserve.calc;

/**
 *
 * @author casper
 */
public interface Calc {
    public long exec(long operand1, char operator, long operand2)
            throws OperationNotSupportedException, OverflowException;
}
