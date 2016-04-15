package com.esgi.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Entity Person
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person", schema = "moviereviewer")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "idperson")
    private int id;
    private String firstName;
    private String name;
    private Date birthday;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "persons")
    private Set<MovieEntity> movies = new HashSet<MovieEntity>();

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
    }
}
