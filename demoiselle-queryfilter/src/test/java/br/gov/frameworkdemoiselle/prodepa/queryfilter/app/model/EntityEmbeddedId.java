package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EntityEmbeddedId {

    public EntityEmbeddedId() {
    }

    public EntityEmbeddedId(EmbeddedIdDummy id, String justString) {
        this.id = id;
        this.justString = justString;
    }

    @EmbeddedId
    private EmbeddedIdDummy id;

    private String justString;

    public EmbeddedIdDummy getId() {
        return id;
    }

    public void setId(EmbeddedIdDummy id) {
        this.id = id;
    }

    public String getJustString() {
        return justString;
    }

    public void setJustString(String justString) {
        this.justString = justString;
    }
}
