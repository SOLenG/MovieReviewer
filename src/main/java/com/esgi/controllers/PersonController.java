package com.esgi.controllers;

import com.esgi.model.MovieEntity;
import com.esgi.model.Utils.MovieUtils;
import com.esgi.model.Person;
import com.esgi.model.Utils.PersonUtils;
import com.esgi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
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

    @RequestMapping(value = "/", method = GET)
    public List<Person> all() {
        return personService.getAllPersons();
    }

    /**
     * @param namePerson String
     * @return Person
     */
    @RequestMapping(value = "/{namePerson:[A-z]*}", method = GET)
    public Person retrieveByName(@ModelAttribute PersonUtils personUtils) {
        Person person = personService.getPerson(Long.parseLong(personUtils.getResearch()));
        if( person != null) {
            person = searchPerson(personUtils,new Person());
        }
        return person;
    }

    public String searchPerson(@ModelAttribute PersonUtils personUtils, Model model) {
        if (!personUtils.getResearch().equals("")) {
            try {
                InputStream is = new URL(URL_API+personUtils.getResearch()).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                JsonObject body = Json.createReader(new StringReader(parseJsonFromReader(rd))).readObject();
                ArrayList<Person> listPersons = parsePersonListFromAPI(body.getJsonObject("feed").getJsonArray("movie"));
                model.addAttribute("listMovies", listPersons);
            }
            catch (IOException error) {
                System.out.println(error);
            }
        }
        return ("persons");
    }

    private ArrayList<Person> parsePersonListFromAPI(JsonArray listPersonFromApi) {
        ArrayList<Person> listMovies = new ArrayList();
        for (int i = 0; i<listPersonFromApi.size(); i++) {
            JsonObject movieJson = listPersonFromApi.getJsonObject(i);
            Person person = new Person();
            person.set(movieJson.getString("originalTitle"));

            if (movieJson.get("poster") != null) {
                person.setImageUrl(((JsonObject)movieJson.get("poster")).getString("href"));
            }

            listPerson.add(person);
        }

        return (listMovies);
    }
}