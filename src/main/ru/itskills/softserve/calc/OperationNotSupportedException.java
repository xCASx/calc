/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itskills.softserve.calc;

/**
 *
 * @author casper
 */
class OperationNotSupportedException extends Exception {
    OperationNotSupportedException() {
        super("Operation not supported");
    }
}
