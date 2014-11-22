package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Manufacturer {

    public Manufacturer(){}

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private int id;
    private String name;

    @OneToMany
    private Set<Product> products;

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

    public Set<Product> getProducts() {
        if(products == null){
            products = new HashSet<Product>();
        }

        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}