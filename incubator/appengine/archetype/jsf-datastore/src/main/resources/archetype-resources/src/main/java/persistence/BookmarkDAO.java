package ${package}.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.Crud;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import ${package}.domain.Bookmark;

@PersistenceController
public class BookmarkDAO implements Crud<Bookmark, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DatastoreService datastoreService;

	@Override
	public void delete(Long id) {
		Key key = createKey(id);
		datastoreService.delete(key);
	}

	@Override
	public List<Bookmark> findAll() {
		List<Bookmark> result = new ArrayList<Bookmark>();

		Query query = new Query(getKind());
		PreparedQuery preparedQuery = datastoreService.prepare(query);

		for (Entity entity : preparedQuery.asIterable()) {
			result.add(parse(entity));
		}

		return result;
	}

	@Override
	public void insert(Bookmark bookmark) {
		Entity entity = new Entity(getKind());

		setProperty(entity, "description", bookmark.getDescription());
		setProperty(entity, "link", bookmark.getLink());

		Key key = datastoreService.put(entity);
		bookmark.setId(key.getId());
	}

	@Override
	public Bookmark load(Long id) {
		Entity entity = loadEntity(id);
		return parse(entity);
	}

	private Entity loadEntity(Long id) {
		Entity result;

		try {
			Key key = createKey(id);
			result = datastoreService.get(key);

		} catch (EntityNotFoundException e) {
			result = null;
		}

		return result;
	}

	@Override
	public void update(Bookmark bookmark) {
		Entity entity = loadEntity(bookmark.getId());
		
		boolean updated = false;
		updated |= setProperty(entity, "description", bookmark.getDescription());
		updated |= setProperty(entity, "link", bookmark.getLink());

		if (updated) {
			datastoreService.put(entity);
		}
	}

	private String getKind() {
		return this.getClass().getName();
	}

	private Key createKey(Long id) {
		return KeyFactory.createKey(getKind(), id);
	}

	private static Bookmark parse(Entity entity) {
		Bookmark result = null;

		if (entity != null) {
			result = new Bookmark();

			result.setId(entity.getKey().getId());
			result.setDescription((String) entity.getProperty("description"));
			result.setLink((String) entity.getProperty("link"));
		}

		return result;
	}

	private boolean setProperty(Entity entity, String property, Object value) {
		boolean updated = false;

		Object currentValue = entity.getProperty(property);

		if (value == null && currentValue != null) {
			entity.removeProperty(property);
			updated = true;

		} else if (value != null && !value.equals(currentValue)) {
			entity.setProperty(property, value);
			updated = true;
		}

		return updated;
	}
}
