package edu.kpi.iasa.workshop.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlists")
@ToString
public class Playlist {
    @Id
    private Long id;

    @NotNull
    @Column(name = "Name")
    private String name;

    @NotNull
    @Column(name = "Length_ms")
    private int length_ms;

    @NotNull
    @Column(name = "Size_bt")
    private int size_bt;

    @NotNull
    @Column(name = "Song_number")
    private int song_number;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length_ms;
    }

    public void setLength(int length_ms) {
        this.length_ms = length_ms;
    }

    public int getSize() {
        return size_bt;
    }

    public void setSize(int size_bt) {
        this.size_bt = size_bt;
    }

    public int getSong_number() {
        return song_number;
    }

    public void setSong_number(int song_number) {
        this.song_number = song_number;
    }


}
