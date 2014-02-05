/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.internal;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.serpro.audit.queue.Consumer;

/**
 *
 * @author 70744416353
 */
public class Auditing {

    @Startup
    public void Auditing() {
        Runnable r = new Consumer();
        Thread thr = new Thread(r);
        thr.start();
    }

}
