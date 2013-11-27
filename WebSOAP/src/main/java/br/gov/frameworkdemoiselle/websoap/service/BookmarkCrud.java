/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.websoap.service;

import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.websoap.business.BookmarkBC;
import br.gov.frameworkdemoiselle.websoap.domain.Bookmark;
import java.io.Serializable;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author 70744416353
 */
@WebService(serviceName = "BookmarkCrud")
public class BookmarkCrud {

    BookmarkBC bbc = Beans.getReference(BookmarkBC.class);

    @WebMethod(operationName = "findAll")
    public List<Bookmark> findAll() {
        return bbc.findAll();
    }

    @WebMethod(operationName = "load")
    public Bookmark load(Long id) {
        return bbc.load(id);
    }

    @WebMethod(operationName = "insert")
    public void insert(Bookmark bookmark) {
        bbc.insert(bookmark);
    }

    @WebMethod(operationName = "update")
    public void update(Bookmark bookmark) {
        bbc.update(bookmark);
    }

    @WebMethod(operationName = "delete")
    public void delete(Long id) {
        bbc.delete(id);
    }
}
