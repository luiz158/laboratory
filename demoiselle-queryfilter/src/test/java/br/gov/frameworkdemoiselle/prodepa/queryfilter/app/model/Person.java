
package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Person {

    public Person() {

    }

    public Person(int id, String name, String nickName,
                  Integer clothesInCloset, Calendar birthDayDate,
                  Calendar firstKissDate, float weight, double height,
                  Long socialSecurityNumber, Integer shoesInCloset,
                  Long totalBooksOwned, Date firstJobDate, Date firstSoccerMatchDate, boolean brazilian, boolean japanese) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.clothesInCloset = clothesInCloset;
        this.birthDayDate = birthDayDate;
        this.firstKissDate = firstKissDate;
        this.weight = weight;
        this.height = height;
        this.socialSecurityNumber = socialSecurityNumber;
        this.shoesInCloset = shoesInCloset;
        this.totalBooksOwned = totalBooksOwned;
        this.firstJobDate = firstJobDate;
        this.firstSoccerMatchDate = firstSoccerMatchDate;
        this.brazilian = brazilian;
        this.japanese = japanese;
    }

    @Id
    private int id;

    private String name;
    private String nickName;

    private Integer clothesInCloset;
                     
    private Integer shoesInCloset;

    private Long totalBooksOwned;
    private Long socialSecurityNumber;

    private boolean brazilian;
    private boolean japanese;

    private float weight;
    private double height;

    @Temporal(TemporalType.DATE)
    private Date firstJobDate;

    @Temporal(TemporalType.DATE)
    private Date firstSoccerMatchDate;

    @Temporal(TemporalType.DATE)
    private Calendar birthDayDate;

    @Temporal(TemporalType.DATE)
    private Calendar firstKissDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Car car;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Dog> dogs;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Certification> certifications;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Collection<Cat> cats;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getClothesInCloset() {
        return clothesInCloset;
    }

    public void setClothesInCloset(Integer clothesInCloset) {
        this.clothesInCloset = clothesInCloset;
    }

    public Calendar getBirthDayDate() {
        return birthDayDate;
    }

    public void setBirthDayDate(Calendar birthDayDate) {
        this.birthDayDate = birthDayDate;
    }

    public Calendar getFirstKissDate() {
        return firstKissDate;
    }

    public void setFirstKissDate(Calendar firstKissDate) {
        this.firstKissDate = firstKissDate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        return id * 33;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return person.id == id;
        }

        return false;
    }

    public Long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(Long socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Integer getShoesInCloset() {
        return shoesInCloset;
    }

    public void setShoesInCloset(Integer shoesInCloset) {
        this.shoesInCloset = shoesInCloset;
    }

    public Long getTotalBooksOwned() {
        return totalBooksOwned;
    }

    public void setTotalBooksOwned(Long totalBooksOwned) {
        this.totalBooksOwned = totalBooksOwned;
    }

    public Date getFirstJobDate() {
        return firstJobDate;
    }

    public void setFirstJobDate(Date firstJobDate) {
        this.firstJobDate = firstJobDate;
    }

    public Date getFirstSoccerMatchDate() {
        return firstSoccerMatchDate;
    }

    public void setFirstSoccerMatchDate(Date firstSoccerMatchDate) {
        this.firstSoccerMatchDate = firstSoccerMatchDate;
    }

    public boolean isBrazilian() {
        return brazilian;
    }

    public void setBrazilian(boolean brazilian) {
        this.brazilian = brazilian;
    }

    public boolean isJapanese() {
        return japanese;
    }

    public void setJapanese(boolean japanese) {
        this.japanese = japanese;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Dog> getDogs() {
        if (dogs == null) {
            dogs = new ArrayList<Dog>();
        }

        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public Set<Certification> getCertifications() {
        if (certifications == null) {
            certifications = new HashSet<Certification>();
        }

        return certifications;
    }

    public void setCertifications(Set<Certification> certifications) {
        this.certifications = certifications;
    }

    public Collection<Cat> getCats() {
        if (cats == null) {
            cats = new ArrayList<Cat>();
        }

        return cats;
    }

    public void setCats(Collection<Cat> cats) {
        this.cats = cats;
    }
}