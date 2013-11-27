package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmbeddedIdDummy implements Serializable{
    private int idInteger;
    private String idString;

    public EmbeddedIdDummy() {
    }

    public EmbeddedIdDummy(int idInteger, String idString) {
        this.idInteger = idInteger;
        this.idString = idString;
    }

    public int getIdInteger() {
        return idInteger;
    }

    public void setIdInteger(int idInteger) {
        this.idInteger = idInteger;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmbeddedIdDummy that = (EmbeddedIdDummy) o;

        if (idInteger != that.idInteger) return false;
        if (!idString.equals(that.idString)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idInteger;
        result = 31 * result + idString.hashCode();
        return result;
    }
}