package exp.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import exp.business.FavoritoBC;
import exp.entity.Favorito;

@ViewController
@NextView("/favorito_edit.xhtml")
@PreviousView("/favorito_list.xhtml")
public class FavoritoListMB extends AbstractListPageBean<Favorito, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private FavoritoBC bc;

	@Inject
	@Path("reports/Favoritos.jrxml")
	private Report relatorio;

	@Inject
	private FileRenderer renderer;

	@Override
	protected List<Favorito> handleResultList() {
		return this.bc.findAll();
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

	public String showReport() {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("titulo", "Relatório de teste");

		byte[] buffer = this.relatorio.export(getResultList(), param, Type.PDF);
		this.renderer.render(buffer, FileRenderer.ContentType.PDF, "relatorio.pdf");

		return getNextView();
	}
}
