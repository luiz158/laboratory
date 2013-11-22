package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Song {

    @Id
    private int id;

    private String name;

    private String artist;

    private int length;
    private Long totalDownloads;
    private float weight;
    private double price;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Temporal(TemporalType.DATE)
    private Calendar creationDate;

    @Enumerated(EnumType.STRING)
    private SongType type;

    public Song(){

    }

    public Song(int id, Calendar creationDate, String name, String artist, int length, Long totalDownloads, float weight, double price, Date releaseDate, SongType type) {
        this.creationDate = creationDate;
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.totalDownloads = totalDownloads;
        this.weight = weight;
        this.price = price;
        this.releaseDate = releaseDate;
        this.type = type;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String group) {
        this.artist = group;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Long getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(Long totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song){
            Song song = (Song) obj;
            return song.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public SongType getType() {
        return type;
    }

    public void setType(SongType type) {
        this.type = type;
    }
}