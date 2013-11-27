package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class DummyEntity {

    @Id
    private int id;

    private String justString;

    @ManyToMany
    @JoinTable(name = "dummy_join_table")
    private List<DummyEntity> autoRelationship;

    public DummyEntity() {
    }

    public DummyEntity(int id, String justString) {
        this.id = id;
        this.justString = justString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
