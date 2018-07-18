package nyt.model.db;

import java.util.ArrayList;

public class DBSearch {

    // set the keyword so we can see it in the response
    // the number of articles (rows from the db) that contain the keyword
    // BONUS: the total number of times that keyword is found in the DB
    // an array of article objects that contain the keyword
    // (you can return 1 or all the columns from the table/object - up to you)
    // for instance, you could just select & set the article headline


    String keyword;
    int articlesContainingKeyword;
    int numTimesKeywordFound;
    ArrayList<Article> articles;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getArticlesContainingKeyword() {
        return articlesContainingKeyword;
    }

    public void setArticlesContainingKeyword(int articlesContainingKeyword) {
        this.articlesContainingKeyword = articlesContainingKeyword;
    }

    public int getNumTimesKeywordFound() {
        return numTimesKeywordFound;
    }

    public void setNumTimesKeywordFound(int numTimesKeywordFound) {
        this.numTimesKeywordFound = numTimesKeywordFound;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
