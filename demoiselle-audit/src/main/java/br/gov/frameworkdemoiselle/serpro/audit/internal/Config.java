/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.internal;

import br.gov.frameworkdemoiselle.configuration.Configuration;

/**
 *
 * @author 70744416353
 */
@Configuration(prefix = "frameworkdemoiselle.audit.")
public class Config  {

    /**
     *
     */
    public static int countQueue;

    /**
     *
     */
    public static int timeQueue;

    /**
     *
     */
    public static String system;

    private Config() {
    }

}
