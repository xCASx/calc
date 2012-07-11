package ru.itskills.softserve.calc;

public class Calculator implements Calc {
    
    public long exec(long operand1, char operator, long operand2)
            throws OperationNotSupportedException, OverflowException {
        long result;
        
        switch (operator) {
            case '+' : {
                result = operand1 + operand2;
                if ((operand1 > 0 && operand2 > 0 && result <= 0) ||
                        (operand1 < 0 && operand2 < 0 && result >= 0)) {
                    throw new OverflowException();
                }
                break;
            }
            case '-' : {
                result = operand1 - operand2;
                if ((operand1 < 0 && operand2 > 0 && result >= 0) ||
                        (operand1 > 0 && operand2 < 0 && result <= 0)) {
                    throw new OverflowException();
                }
                break;
            }
            case '*' : {
                result = operand1 * operand2;
                if ((operand1 != 0 && operand2 != 0) &&
                        ((operand1 > 0 && operand2 < 0 && result >= 0) ||
                        (operand1 < 0 && operand2 > 0 && result >= 0) ||
                        (operand1 > 0 && operand2 > 0 && result <= 0) ||
                        (operand1 < 0 && operand2 < 0 && result <= 0))) {
                    throw new OverflowException();
                }
                break;
            }
            case '/' : result = operand1 / operand2;
                break;
            default : throw new OperationNotSupportedException(); 
        }
        
        return result;
    }
    
}