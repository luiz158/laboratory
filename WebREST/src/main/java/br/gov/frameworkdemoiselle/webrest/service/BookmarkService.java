/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.webrest.service;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.webrest.business.BookmarkBC;
import br.gov.frameworkdemoiselle.webrest.domain.Bookmark;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 70744416353
 */
@Path("/BookmarkCrud")
public class BookmarkService {

    @Inject
    BookmarkBC bbc;

    /**
     *
     * @return
     */
    @GET
    @Path("/findAllXML")
    @Produces(MediaType.TEXT_XML)
    public List<Bookmark> findAllXML() {
        return bbc.findAll();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bookmark> findAll() {
        return bbc.findAll();
    }

    @GET
    @Path("/load/{bookmarkid}")
    @Produces(MediaType.TEXT_HTML)
    public Bookmark load(@PathParam("bookmarkid") Long bookmarkid) {
        return bbc.load(bookmarkid);
    }
}
