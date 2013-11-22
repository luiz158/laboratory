package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Dog {

    @Id
    private int id;

    private String name;
    private double weight;
    private float hairSize;
    private long fleasTotal;
    private int toysTotal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfFirstPuppyBirth;

    @ManyToOne
    private Person person;

    public Dog() {

    }

    public Dog(int id, String name, double weight, Date dateOfBirth, float hairSize, long fleasTotal, int toysTotal) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.hairSize = hairSize;
        this.fleasTotal = fleasTotal;
        this.toysTotal = toysTotal;
    }

    public static void main(String[] args) {

        System.out.println(new Date());
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDateOfFirstPuppyBirth() {
        return dateOfFirstPuppyBirth;
    }

    public void setDateOfFirstPuppyBirth(Date dateOfFirstPuppyBirth) {
        this.dateOfFirstPuppyBirth = dateOfFirstPuppyBirth;
    }

    public float getHairSize() {
        return hairSize;
    }

    public void setHairSize(float hairSize) {
        this.hairSize = hairSize;
    }

    public long getFleasTotal() {
        return fleasTotal;
    }

    public void setFleasTotal(long fleasTotal) {
        this.fleasTotal = fleasTotal;
    }

    public int getToysTotal() {
        return toysTotal;
    }

    public void setToysTotal(int toysTotal) {
        this.toysTotal = toysTotal;
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dog) {
            Dog dog = (Dog) obj;
            return dog.getId() == getId();
        }

        return false;
    }
}