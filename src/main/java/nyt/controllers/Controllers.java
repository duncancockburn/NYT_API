package nyt.controllers;

import nyt.model.db.DBSearch;
import nyt.model.db.WordCountObject;
import nyt.model.nyt.NYTRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nyt.service.NYTService;

@RestController

@RequestMapping("/nyt")
public class Controllers {

    @Autowired
    NYTService service;


    @RequestMapping("/search")
    public NYTRoot searchNYT(
            @RequestParam("keyword") String keyword1,
            @RequestParam("persist") String persist1) {

        NYTRoot response = service.searchNYT(keyword1, persist1);

        return response;

    }

    @RequestMapping("/headlineSearch")
    public NYTRoot headlineSearchNYT(
            @RequestParam("keyword") String keyword2,
            @RequestParam("persist") String persist2) {

        NYTRoot response = service.searchNYT(keyword2, persist2);

        return response;
    }


    @RequestMapping("/searchDB")
    public DBSearch searchDB(
            @RequestParam("keyword") String keyword,
            @RequestParam("searchWords") String[] searchWords) {

        return service.searchDB(keyword);
    }


    @RequestMapping("/wordCount")
    public WordCountObject wordCount(
            @RequestParam("keyword") String keyword,
            @RequestParam("searchWords") String[] searchTerms) {

        return service.wordCount(keyword, searchTerms);
    }
}


