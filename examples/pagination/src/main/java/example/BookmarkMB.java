package example;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class BookmarkMB {

	@Inject
	private BookmarkBC bc;

	private LazyDataModel<Bookmark> dataModel;
	
	@Inject
	private PaginationContext paginationContext;

	public BookmarkMB() {
		dataModel = new LazyDataModel<Bookmark>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Bookmark> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Pagination pagination = getPagination();
				pagination.setFirstResult(first);
				pagination.setPageSize(pageSize);

				List<Bookmark> retorno = bc.findAll();

				dataModel.setRowCount(pagination.getTotalResults());

				return retorno;
			}
		};
	}

	public LazyDataModel<Bookmark> getDataModel() {
		return dataModel;
	}
	
	public Pagination getPagination() {
		return paginationContext.getPagination(Bookmark.class, true);
	}
	
}
