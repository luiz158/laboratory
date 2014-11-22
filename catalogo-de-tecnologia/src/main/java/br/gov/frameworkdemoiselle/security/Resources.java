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
public class Resources implements IResources {

    /**
     *
     * @param id
     * @return
     */
    public static String getResources(int id) {

        return "";
    }

    /**
     *
     * @param resource
     * @return
     */
    public static Integer getResource(String resource) {
        Integer number = new Integer(0);

        if (resource.equals(ATRIBUTO)) {
            number = 1;
        }
        if (resource.equals(DESCRITOR)) {
            number = 2;
        }
        if (resource.equals(EXCECAO)) {
            number = 3;
        }
        if (resource.equals(ORGAO)) {
            number = 4;
        }
        if (resource.equals(PAPEL)) {
            number = 5;
        }
        if (resource.equals(PESQUISAR)) {
            number = 6;
        }
        if (resource.equals(RECURSO)) {
            number = 7;
        }
        if (resource.equals(SECURITY)) {
            number = 8;
        }
        if (resource.equals(SERVICO)) {
            number = 9;
        }
        if (resource.equals(SISTEMA)) {
            number = 10;
        }
        if (resource.equals(MARCACAO)) {
            number = 12;
        }
        if (resource.equals(USUARIO)) {
            number = 13;
        }
        if (resource.equals(INVALID)) {
            number = 99;
        }

        return number;
    }

    /**
     *
     * @return
     */
    public static List<String> getResourcesList() {
        List<String> lista = new ArrayList<String>();

        lista.add(ATRIBUTO);
        lista.add(DESCRITOR);
        lista.add(EXCECAO);
        lista.add(ORGAO);
        lista.add(PAPEL);
        lista.add(PESQUISAR);
        lista.add(RECURSO);
        lista.add(SECURITY);
        lista.add(SERVICO);
        lista.add(SISTEMA);
        lista.add(MARCACAO);
        lista.add(USUARIO);

        lista.add(INVALID);

        return lista;
    }

    private Resources() {
    }

}
