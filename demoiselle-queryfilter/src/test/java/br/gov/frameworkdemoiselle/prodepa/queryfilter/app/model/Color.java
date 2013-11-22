package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Color {

    public Color(){

    }

    public Color(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private int id;

    @ManyToOne
    private Manufacturer manufacturer;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
