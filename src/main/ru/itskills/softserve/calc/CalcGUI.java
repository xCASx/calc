/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itskills.softserve.calc;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author casper
 */
public class CalcGUI extends JFrame {
    
    boolean firstDigit = true;
    Long operand1 = null;
    Long operand2 = null;
    Character operator = null;
    Calculator calc = new Calculator();
    final JTextField jTextField = new JTextField("0");
    
    CalcGUI () {
        initComponents();
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalcGUI().setVisible(true);
            }
        });
    }

    private void initComponents() {
        
        // Frame params
        this.setResizable(false);
        this.setSize(new Dimension(200, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Layout for frame
        this.setLayout(new BorderLayout(5, 5));
        
        jTextField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(jTextField, BorderLayout.NORTH);
        
        // Numbers panel
        JPanel numberPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        // Button Listener
        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String label = ((JButton)e.getSource()).getText();
                if (firstDigit) {
                    jTextField.setText(label);
                    if (!label.equals("0")) {
                        firstDigit = false;
                    }
                } else if (!(firstDigit && label.equals("0"))) {
                    StringBuilder sb = new StringBuilder(jTextField.getText());
                    sb.append(label);
                    jTextField.setText(sb.toString());
                    firstDigit = false;
                }
            }
        };
        
        // Initialize and add number buttons
        char[] numbers = new char[] {'7','8','9','4','5','6','1','2','3','0'};
        for (char i : numbers) {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(buttonListener);
            numberPanel.add(b);
        }
        
        // Add addition buttons
        JButton clearButtorn = new JButton("C");
        
        clearButtorn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
                jTextField.setText("0");
            }
        });
        
        numberPanel.add(clearButtorn);
        
        JButton resultButton = new JButton("=");
        
        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstDigit = true;
                if (operand2 == null) {
                    operand2 = readNumber();
                }
                calc();
            }
        });
        
        numberPanel.add(resultButton);
        
        // Operations panel
        JPanel operationPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        // Operation Listener
        ActionListener operationListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstDigit = true;
                if (operator != null) {
                    operand2 = null;
                }
                operator = ((JButton)e.getSource()).getText().charAt(0);

                if (operand2 != null) {
                    calc();
                } else {
                    operand1 = readNumber();
                }
            }
        };
        
        // Initialize and add operation buttons
        char[] operations = new char[] {'/', '*', '-', '+'};
        for (char i : operations) {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(operationListener);
            operationPanel.add(b);
        }
        
        // Add panels to frame
        this.add(numberPanel, BorderLayout.CENTER);
        this.add(operationPanel, BorderLayout.EAST);
        
    }
    
    private void calc() {
        try {
            operand1 = calc.exec(operand1, operator, operand2);
            jTextField.setText(operand1.toString());
        } catch (OperationNotSupportedException ex) {
        } catch (OverflowException ex) {
            clear();
            jTextField.setText("Overflow");
        } catch (ArithmeticException ex) {
            clear();
            jTextField.setText("Devision by zero");
        } catch (NullPointerException ex) {
            clear();
            jTextField.setText("Format exception");
        }
    }
    
    private Long readNumber() {
        Long l = null;
        try {
             l = Long.parseLong(jTextField.getText());
        } catch (NumberFormatException ex) {
            clear();
            jTextField.setText("Number format exception");
        }
        return l;
    }
    
    private void clear() {
        firstDigit = true;
        operand1 = null;
        operand2 = null;
        operator = null;
    }
    
}
