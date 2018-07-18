package nyt.service;

import nyt.mappers.NYTMapper;
import nyt.model.db.Article;
import nyt.model.db.DBSearch;
import nyt.model.db.WordCountObject;
import nyt.model.nyt.Docs;
import nyt.model.nyt.NYTRoot;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;


@Service
public class NYTService {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NYTMapper mapper;

    private final String API_KEY = "4831cb5ac3e34a2d83974365787fe708";


    public NYTRoot searchNYT(String keyword, String persist) {

        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=" + keyword + "&api-key=" + API_KEY;

        NYTRoot response = restTemplate.getForObject(url, NYTRoot.class);

        if (persist.equalsIgnoreCase("true")) {
            persistResults(response);
        }

        return response;
    }


    /**
     * mapping the results of the api into the database
     *
     * @param data
     */
    private void persistResults(NYTRoot data) {
        // for each element of the response call insert
        for (Docs doc : data.getResponse().getDocs()) {
            // creating a temp
            Article a = new Article();

            a.setHeadline(doc.getHeadline().getMain());
            a.setPub_date(doc.getPub_date());
            a.setSnippet(doc.getSnippet());
            a.setUrl(doc.getWeb_url());

            // calling insert
            mapper.insertRecord(a);
        }

    }


    public ArrayList<Article> getAllTopStories(String query) {

        if (!query.equalsIgnoreCase("null")) {
            return null;
        } else {
            return mapper.getAllTopStories();
        }
    }


    public ArrayList<Article> searchBySection(String section) {
        int tempId = mapper.checkIfSectionExists(section);
        return mapper.searchBySection(section);
    }


    public Integer url(String url) {
        int tempId = mapper.getStoryId(url);
        return tempId;
    }


    public DBSearch searchDB(String keyword) {
        DBSearch value = new DBSearch();
        value.setKeyword(keyword);                         //setting the key word in the DBSearch = object

        String keywordLike = "%" + keyword + "%";           //this is what i want to chuck through the articles keywork

        value.setArticles(mapper.articlesKeyword(keywordLike)); //setting articles as an arraylist of articles


        int tempCount = 0;
        for (Article a : value.getArticles()) {
            if (a.getHeadline().contains(keywordLike)) {
                tempCount++;
            }
            if (a.getSnippet().contains(keywordLike)) {
                tempCount++;
            }
        }
        return searchDB(keywordLike);
    }


    public WordCountObject wordCount(String keyword, String[] searchTerms) {
        NYTRoot root = searchNYT(keyword, "false");

        WordCountObject retVal = new WordCountObject();

        retVal.setKeyword(keyword);
        retVal.setSearchTerms(searchTerms);

        Integer headlineCount, snippetCount, totalCount;

        for (Docs docs : root.getResponse().getDocs()) {
            for (String term : searchTerms) {
                headlineCount = StringUtils.countMatches(docs.getHeadline().getPrint_headline(), term);
                snippetCount = StringUtils.countMatches(docs.getSnippet(), term);
                totalCount = headlineCount + snippetCount;

                retVal.getMap().merge(term, totalCount, Integer::sum);
            }

            retVal.setArticles(retVal.getArticles());

        }return retVal;
    }
}



    // Just FYI, searchWords is a comma separated list of words that are provided in the url - they will land in your
    // method as an array

    // If useDb is true - return a response that shows the total number of times each keyword appears in the database,
    // and how many distinct articles it appears in

    // Is useDb is false - make a call to the NYT api and scan through the entire results and count the number of times
    // each word appears in the NYT API result using a WordCountObject


