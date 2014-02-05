/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.queue;

import br.gov.frameworkdemoiselle.serpro.audit.business.TrilhaBC;
import br.gov.frameworkdemoiselle.serpro.audit.domain.Trilha;
import br.gov.frameworkdemoiselle.serpro.audit.internal.Auditing;
import br.gov.frameworkdemoiselle.serpro.audit.internal.Config;
import br.gov.frameworkdemoiselle.util.Beans;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 70744416353
 */
public class Consumer implements Runnable {

    //@Inject
    private final TrilhaBC tbc = Beans.getReference(TrilhaBC.class);

    @Override
    public void run() {
        while (true) {
            if (!QueueTrilha.isEmpty()) {
                Trilha trilha = QueueTrilha.poll();
                tbc.insert(trilha);
                System.gc();
            }
            try {
                Thread.sleep(Config.timeQueue);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auditing.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
