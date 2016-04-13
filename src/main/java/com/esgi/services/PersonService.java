package com.esgi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.esgi.repository.PersonRepository;
import com.esgi.model.Person;

import java.util.List;

/**
 * Class PersonService
 */
@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person getPerson(long id) {
        return (personRepository.findOne(id));
    }

    @Transactional
    public List<Person> getAllPerson() {
        return(personRepository.findAll());
    }
}
