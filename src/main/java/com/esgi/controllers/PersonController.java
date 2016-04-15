package com.esgi.controllers;

import com.esgi.model.Person;
import com.esgi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonArray;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Class PersonController
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {

    private PersonService personService;

    /**
     * @param personService PersonService
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * @param idPerson String
     * @return Person
     */
    @RequestMapping(value = "/search", method = GET)
    public Person retrieve(@RequestParam("id") String idPerson) {
        return personService.getPerson(Long.parseLong(idPerson));
    }

    private ArrayList<Person> parsePersonListFromAPI(JsonArray listMovieFromApi) {
        ArrayList<Person> listPerson = new ArrayList();

        return (listPerson);
    }
}