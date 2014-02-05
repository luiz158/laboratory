package br.gov.frameworkdemoiselle.view;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.business.UsuarioBC;
import br.gov.frameworkdemoiselle.domain.Usuario;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author escritorio
 */
@ViewController
@NextView("./usuario_edit.jsf")
@PreviousView("./usuario_list.jsf")
public class UsuarioListMB extends AbstractListPageBean<Usuario, Long> {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioBC usuarioBC;

    /**
     *
     * @return
     */
    @Override
    protected List<Usuario> handleResultList() {
        return this.usuarioBC.findAll();
    }

    /**
     *
     * @return
     */
    public List<Usuario> getUsuarioFiltro() {
        return Collections.unmodifiableList(this.usuarioBC.findAll());
    }



}
