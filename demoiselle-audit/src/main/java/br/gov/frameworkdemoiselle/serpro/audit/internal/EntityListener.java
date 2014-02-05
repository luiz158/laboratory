/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.internal;

import br.gov.frameworkdemoiselle.serpro.audit.domain.Trilha;
import br.gov.frameworkdemoiselle.serpro.audit.queue.QueueTrilha;
import java.io.Serializable;
import javax.persistence.PostPersist;

/**
 *
 * @author 70744416353
 */
public class EntityListener implements Serializable {

    //@Inject
    //private Event<Trilha> entidadeAudit = Beans.getReference(Event.class);

    @PostPersist
    public void postPersist(Object object) {
        Trilha trilha = new Trilha();
        trilha.setSistema(Config.system);
        trilha.setQuando(System.currentTimeMillis());
        trilha.setObjserial(object.toString());
       // entidadeAudit.fire(trilha);
        while (!QueueTrilha.offer(trilha));

    }

}
