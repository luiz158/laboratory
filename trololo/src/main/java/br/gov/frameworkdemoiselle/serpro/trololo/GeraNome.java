/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.frameworkdemoiselle.serpro.trololo;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author 70744416353
 */
public class GeraNome {

    private Random rd = new Random(5);

    private String[] nomeMasculino = {"João", "José", "Francisco", "Roberto", "Renato"};
    private String[] nomeFeminino = {"Maria", "Ana", "Roberta", "Cláudia", "Renata"};
    private String[] sobreNome = {"Pinheiro", "Oliveira", "Souza", "Santos", "Silva"};

    public String getNomeMasculino() {
        return nomeMasculino[rd.nextInt()];
    }

    public String getNomeFeminino() {
        return nomeFeminino[rd.nextInt()];
    }

    public String getSobreNome() {
        return sobreNome[rd.nextInt()];
    }

    private int random(String[] array) {

        int retorno = rd.nextInt(array.length);
        if (retorno == array.length) {
            retorno--;
        }

        return retorno;
    }

}
