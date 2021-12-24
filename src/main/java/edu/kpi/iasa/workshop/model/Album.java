package edu.kpi.iasa.workshop.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "albums")
@ToString
public class Album {
    @Id
    private Long id;

    @NotNull
    @Column(name = "Title")
    private String title;

    @NotNull
    @Column(name = "Genre")
    private String genre;

    @NotNull
    @Column(name = "Publication_Year")
    private int pubYear;

    @NotNull
    @Column(name = "Song_number")
    private int songNumber;

    @NotNull
    @Column(name = "Artist_id")
    private int artist;

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public int getArtist() {
        return artist;
    }

    public void setArtist(int artist) {
        this.artist = artist;
    }
}
