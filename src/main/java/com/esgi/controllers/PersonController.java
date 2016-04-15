package com.esgi.controllers;

import com.esgi.model.Person;
import com.esgi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonArray;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Class PersonController
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {

    /**
     * @param personService PersonService
     */
    @Autowired
    private PersonService personService;

    @RequestMapping(method = GET)
    public List<Person> all() {
        return personService.getAllPersons();
    }

    /**
     * @param namePerson String
     * @return Person
     */
    @RequestMapping(value = "/{namePerson:[A-z]*}", method = GET)
    public Person retrieveByName(@PathVariable("namePerson") String namePerson) {
        return personService.getPerson(Long.parseLong(namePerson));
    }

}