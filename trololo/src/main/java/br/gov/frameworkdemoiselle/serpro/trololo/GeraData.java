/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.trololo;

import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author 70744416353
 */
public class GeraData {

    GregorianCalendar gc = new GregorianCalendar();

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


}
