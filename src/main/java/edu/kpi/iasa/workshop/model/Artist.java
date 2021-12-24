package edu.kpi.iasa.workshop.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "artists")
@ToString
public class Artist {
    @Id
    private Long id;

    @NotNull
    @Column(name = "Name")
    private String name;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}
}
