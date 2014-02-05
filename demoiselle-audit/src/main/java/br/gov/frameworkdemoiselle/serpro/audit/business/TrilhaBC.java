/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.serpro.audit.business;

import br.gov.frameworkdemoiselle.serpro.audit.domain.Trilha;
import br.gov.frameworkdemoiselle.serpro.audit.persistence.TrilhaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import javax.enterprise.event.Observes;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class TrilhaBC extends DelegateCrud<Trilha, Long, TrilhaDAO> {

    /**
     *
     * @param trilha
     * @param object
     */
    public void inserir(@Observes Trilha trilha) {
    }





}
