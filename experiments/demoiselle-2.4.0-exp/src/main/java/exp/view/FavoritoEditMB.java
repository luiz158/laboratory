package exp.view;

import javax.inject.Inject;

import exp.business.FavoritoBC;
import exp.entity.Favorito;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("/favorito_list.xhtml")
public class FavoritoEditMB extends AbstractEditPageBean<Favorito, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private FavoritoBC bc;

	@Override
	@Transactional
	public String delete() {
		this.bc.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.bc.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.bc.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Favorito handleLoad(Long id) {
		return this.bc.load(id);
	}

}
