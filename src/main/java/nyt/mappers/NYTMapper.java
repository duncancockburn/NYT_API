package nyt.mappers;


import nyt.model.db.Article;
import nyt.model.db.DBSearch;
import nyt.model.nyt.Docs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface NYTMapper {
    // a query to check if top story already exists in db - using url
    String GET_STORY_ID = "SELECT id FROM `nyt`.`articles` where url= #{url}";

    String INSERT_ARTICLE = "INSERT INTO `nyt`.`articles` (`headline`, `snippet`," +
            " `url`, `pub_date`) VALUES (#{headline}, #{snippet}, #{url}, #{pub_date})";

    String SELECT_ALL_TOP_STORIES = "SELECT * from `nyt`.articles";

    String SELECT_WITH_SEARCH_PARAM = "SELECT * from `nyt`.articles where title like #{query}";

    String SELECT_STORIES_BY_SECTION = "" +
            "SELECT * from `nyt`.articles " +
            "where section= #{section}" +
            "order by updated_date desc " +
            "limit 10";

    String CHECK_SECTION_EXISTS = "" +
            "select id from `second_rds`.nyt_top_stories " +
            "where pub_date = #{pub_date} " +
            "limit 1";

    String ARTICLES_CONTAINING_KEYWORD = "SELECT * FROM nyt.articles where snippet like #{keyword} " +
            "or headline like #{keyword}";

    @Select(ARTICLES_CONTAINING_KEYWORD)
    public ArrayList<Article> articlesKeyword(String keyword);

    @Insert(INSERT_ARTICLE)
    public int insertRecord (Article article);

    @Select(GET_STORY_ID)
    public int getStoryId(String url);

    @Select(SELECT_ALL_TOP_STORIES)
    public ArrayList<Article> getAllTopStories();

    @Select(SELECT_WITH_SEARCH_PARAM)
    public ArrayList<Article> searchTopStories(String query);

    @Select(SELECT_STORIES_BY_SECTION)
    public ArrayList<Article> searchBySection(String section);

    @Select(CHECK_SECTION_EXISTS)
    public int checkIfSectionExists(String section);
}