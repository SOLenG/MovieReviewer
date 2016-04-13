package com.esgi.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Class Entity Person
 */
@Entity
@Table(name = "person", schema = "moviereviewer")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "idperson")
    private int id;

    @Basic
    @Column(name = "firstname")
    private String firstName;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "birthday")
    private Date birthday;

    /*@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER )
    @JoinTable(name="casting",
            joinColumns = @JoinColumn(name="idperson"),
            inverseJoinColumns = @JoinColumn(name="idmovie")
    )*/
    @OneToMany(cascade=CascadeType.ALL)
    private MovieEntity movie;

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @return string
     */
    public String getFirstname() {
        return firstName;
    }

    /**
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * @return string
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday Date
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public MovieEntity getMovie() {
        return movie;
    }
}
