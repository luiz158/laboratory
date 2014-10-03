/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.demoiselle.drails.commands;

import org.demoiselle.drails.exceptions.ValidationException;

/**
 *
 * @author 05081364908
 */
interface Command {
    
    void execute(String line) throws ValidationException;
    
}
