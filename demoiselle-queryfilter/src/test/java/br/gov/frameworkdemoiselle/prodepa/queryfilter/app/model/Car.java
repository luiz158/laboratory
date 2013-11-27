package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Car {

    @Id
    private int id;

    @OneToOne(mappedBy = "car")
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    private Color color;

    private String name;
    private BigDecimal weight;

    public Car() {

    }

    public Car(int id, String name, Color color, BigDecimal weight) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return id * 35;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            Car car = (Car) obj;
            return car.id == id;
        }

        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}