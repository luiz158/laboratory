package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    private int id;
    private String name;

    @ManyToMany
    private List<NickName> nickNames;

    public Product(){}

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public List<NickName> getNickNames() {
        if(nickNames == null){
            nickNames = new ArrayList<NickName>();
        }

        return nickNames;
    }

    public void setNickNames(List<NickName> nickNames) {
        this.nickNames = nickNames;
    }
}