package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class NickName {

    @Id
    private int id;
    private String name;
    private String justString;
    private long justLong;
    private float justFloat;
    private double justDouble;
    private boolean justBoolean;
    private BigDecimal justBigDecimal;

    @Temporal(TemporalType.DATE)
    private Date justDate;

    @Temporal(TemporalType.DATE)
    private Calendar justCalendar;

    @ManyToMany
    @JoinTable(name = "NICKNAME_DUMMY_COLLECTION", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "dummy_id"))
    private Collection<DummyEntity> justCollection;

    @ManyToMany
    @JoinTable(name = "NICKNAME_DUMMY_LIST", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "dummy_id"))
    private List<DummyEntity> justList;

    @ManyToMany
    @JoinTable(name = "NICKNAME_DUMMY_SET", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "dummy_id"))
    private Set<DummyEntity> justSet;

    public NickName() {}

    public NickName(int id, String name, String justString, double justDouble, long justLong, float justFloat, BigDecimal justBigDecimal, Date justDate, Calendar justCalendar, boolean justBoolean) {
        this.id = id;
        this.name = name;
        this.justString = justString;
        this.justDouble = justDouble;
        this.justLong = justLong;
        this.justFloat = justFloat;
        this.justBigDecimal = justBigDecimal;
        this.justDate = justDate;
        this.justCalendar = justCalendar;
        this.justBoolean = justBoolean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJustSet(Set<DummyEntity> justSet) {
        this.justSet = justSet;
    }

    public void setJustList(List<DummyEntity> justList) {
        this.justList = justList;
    }

    public void setJustCollection(Collection<DummyEntity> justCollection) {
        this.justCollection = justCollection;
    }
}