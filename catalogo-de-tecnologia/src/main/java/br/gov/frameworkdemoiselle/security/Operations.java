/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.security;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author escritorio
 */
public class Operations implements IOperations{

    /**
     *
     * @param sum
     * @return
     */
    public static List<String> listOperations(int sum) {
        List<String> list = new ArrayList<String>();
        list.add("LIST");
        switch (sum) {
            case 1:
                list.add(DELETE);
                break;
            case 2:
                list.add(INSERT);
                break;
            case 3:
                list.add(INSERT);
                list.add(UPDATE);
                break;
            case 4:
                list.add(DELETE);
                break;
            case 5:
                list.add(UPDATE);
                list.add(DELETE);
                break;
            case 6:
                list.add(INSERT);
                list.add(DELETE);
                break;
            case 7:
                list.add(UPDATE);
                list.add(INSERT);
                list.add(DELETE);
                break;
            default:
                list.add(INVALID);
                break;
        }
        return list;
       
    }

    /**
     *
     * @param listOp
     * @return
     */
    public static int getOperation(List<String> listOp) {
        int number = 0;
        if (listOp.contains(UPDATE)) {
            number += 1;
        }
        if (listOp.contains(INSERT)) {
            number += 2;
        }
        if (listOp.contains(DELETE)) {
            number += 4;
        }
        return number;
    }

    Operations() {
       
    }
}
