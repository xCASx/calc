package ru.itskills.softserve.calc;

public class CalculatorMock implements Calc{
    
    public long exec(long operand1, char operator, long operand2)
            throws OperationNotSupportedException, OverflowException {
        long result = 0;
        
        switch (operator) {
            case '+' : result = operand1 + operand2;
                break;
            case '-' : result = operand1 - operand2;
                break;
            case '*' : result = operand1 * operand2;
                break;
            
            // TODO: implement the calculation
            case '/' : break;
            
            default : throw new OperationNotSupportedException(); 
        }
        
        if (operator == '+' &&
                ((operand1 > 0 && operand2 > 0 && result <= 0) ||
                (operand1 < 0 && operand2 < 0 && result >= 0))) {
            throw new OverflowException();
        } else if (operator == '-' &&
                ((operand1 < 0 && operand2 > 0 && result >= 0) ||
                (operand1 > 0 && operand2 < 0 && result <= 0))) {
            throw new OverflowException();
        } else if ((operator == '*') &&
                (operand1 != 0 && operand2 != 0) &&
                ((operand1 > 0 && operand2 < 0 && result >= 0) ||
                (operand1 < 0 && operand2 > 0 && result >= 0) ||
                (operand1 > 0 && operand2 > 0 && result <= 0) ||
                (operand1 < 0 && operand2 < 0 && result <= 0))) {
            throw new OverflowException();
        }
        
        return 6L;
    }
    
}