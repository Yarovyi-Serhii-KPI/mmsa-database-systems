package edu.kpi.iasa.workshop.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "songs")
@ToString
public class Song {
    @Id
    private Long id;

    @NotNull
    @Column(name = "Name")
    private String name;

    @NotNull
    @Column(name = "Album_id")
    private int album_id;

    @NotNull
    @Column(name = "Miliseconds")
    private int milliseconds;

    @NotNull
    @Column(name = "Bytes")
    private int bytes;

    @NotNull
    @Column(name = "Uploaded_by")
    private int uploaded_by;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public int getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(int uploaded_by) {
        this.uploaded_by = uploaded_by;
    }
}
