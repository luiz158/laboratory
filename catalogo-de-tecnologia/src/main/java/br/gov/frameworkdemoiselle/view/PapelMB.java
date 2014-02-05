package br.gov.frameworkdemoiselle.view;

import br.gov.frameworkdemoiselle.domain.Recurso;
import br.gov.frameworkdemoiselle.security.Roles;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author escritorio
 */
@ViewController
public class PapelMB extends AbstractEditPageBean<Recurso, Long> {

    private List<String> resultList;

    /**
     *
     * @return
     */
    public List<String> getResultListRoles() {
        this.resultList = Roles.getRolesList();
        return Collections.unmodifiableList(this.resultList);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    protected Recurso handleLoad(Long id) {
       return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String delete() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String insert() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String update() {
        return null;
    }



}
