package nyt.model.db;

import java.util.ArrayList;
import java.util.HashMap;

public class WordCountObject<K,V> {
    String keyword;
    String[] searchTerms;
    ArrayList<Article> articles = new ArrayList();
    HashMap<String, Integer> map = new HashMap<>();

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String[] getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String[] searchTerms) {
        this.searchTerms = searchTerms;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
}
