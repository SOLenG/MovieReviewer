package com.esgi.controllers;

import com.esgi.model.MovieEntity;
import com.esgi.model.Utils.MovieUtils;
import com.esgi.model.Person;
import com.esgi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController {

    private String apiAllocineUrl = "http://api.allocine.fr/rest/v3/search?count=500&filter=movie&format=json&page=1&partner=YW5kcm9pZC12Mg&profile=medium&q=";

    @Autowired
    private MovieService movieService;

    @RequestMapping(method= RequestMethod.GET)
    public String displayMovie(@ModelAttribute MovieEntity movie, Model model) {
        model.addAttribute("movieUtils", new MovieUtils());
        if (movie.getCodeAllocine() != 0) {
            System.out.println(movie.getTitle());
        }

        return("movies");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchMovies(@ModelAttribute MovieUtils movieUtils, Model model) {
        if (!movieUtils.getResearch().equals("")) {
            try {
                InputStream is = new URL(apiAllocineUrl+movieUtils.getResearch()).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                JsonObject body = Json.createReader(new StringReader(parseJsonFromReader(rd))).readObject();
                ArrayList<MovieEntity> listMovies = parseMovieListFromAPI(body.getJsonObject("feed").getJsonArray("movie"));
                model.addAttribute("listMovies", listMovies);
            }
            catch (IOException error) {
                System.out.println(error);
            }
        }
        return ("movies");
    }

    private ArrayList<MovieEntity> parseMovieListFromAPI(JsonArray listMovieFromApi) {
        ArrayList<MovieEntity> listMovies = new ArrayList();
        for (int i = 0; i<listMovieFromApi.size(); i++) {
            JsonObject movieJson = listMovieFromApi.getJsonObject(i);
            MovieEntity movie = new MovieEntity();
            movie.setTitle(movieJson.getString("originalTitle"));

            if (movieJson.get("poster") != null) {
                movie.setImageUrl(((JsonObject)movieJson.get("poster")).getString("href"));
            }

            //movie.setDate_release(new Date(((JsonObject)movieJson.get("release")).getString("releaseDate")));
            if(movieJson.get("castingShort") != null){
                HashSet<Person> persons = new HashSet<>();
                Person person = new Person();
                person.setName(((JsonObject)movieJson.get("castingShort")).getString("directors"));
                persons.add(person);
                movie.setPersons(persons);
            }

            movie.setCodeAllocine(movieJson.getJsonNumber("code").intValue());
            if (movieJson.get("statistics") != null) {
                movie.setNoteAllocine(((JsonObject)movieJson.get("statistics")).getJsonNumber("userRating").bigDecimalValue().setScale(2, BigDecimal.ROUND_FLOOR).floatValue());
            }

            listMovies.add(movie);
        }

        return (listMovies);
    }
}