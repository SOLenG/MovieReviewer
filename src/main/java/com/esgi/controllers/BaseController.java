package com.esgi.controllers;

import com.esgi.MovieReviewerApplication;
import com.esgi.model.Utils.BaseUtils;
import lombok.Value;

import java.io.IOException;
import java.io.Reader;

/**
 * Class BaseController
 */
abstract class BaseController {
    @Value("${moviereviewer.webservice.url}")
    protected final String URL_API;
    @Value("${moviereviewer.webservice.params.query}")
    protected final String URL_GET_SEARCH;
    @Value("${moviereviewer.webservice.params.filter}")
    protected final String URL_GET_FILTER;

    // Filters
    private static final String FILTER_MOVIE = "movie";
    private static final String FILTER_PERSON = "person";

    protected String parseJsonFromReader(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
