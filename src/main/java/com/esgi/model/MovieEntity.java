package com.esgi.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Arnaud Flaesch on 09/04/2016.
 */
@Entity
@Table(name = "movie", schema = "moviereviewer")
public class MovieEntity {
    private int idmovie;
    private String title;
    private String imageUrl;
    private String description;
    private Date dateRelease;
    private int codeAllocine;
    private float noteAllocine;

    private Set<Person> persons = new HashSet<Person>();

    @Id
    @GeneratedValue
    @Column(name = "idmovie")
    public int getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(int idmovie) {
        this.idmovie = idmovie;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date_release")
    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    @Basic
    @Column(name = "code_allocine")
    public int getCodeAllocine() {
        return codeAllocine;
    }

    public void setCodeAllocine(int codeAllocine) {
        this.codeAllocine = codeAllocine;
    }

    public float getNoteAllocine() {
        return noteAllocine;
    }

    public void setNoteAllocine(float noteAllocine) {
        this.noteAllocine = noteAllocine;
    }

    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name="idgenre")
    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    //@NotEmpty
    @ManyToMany( fetch=FetchType.LAZY , cascade = CascadeType.ALL )
    @JoinTable(name="movie",
            joinColumns = @JoinColumn(name="idmovie"),
            inverseJoinColumns = @JoinColumn(name="idperson")
    )
    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
