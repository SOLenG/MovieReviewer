package com.esgi.controllers;

import java.io.IOException;
import java.io.Reader;

/**
 * Class BaseController
 */
abstract class BaseController {

    protected String parseJsonFromReader(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
