package kr.co.casa_int.service;

import kr.co.casa_int.entity.Article;
import kr.co.casa_int.servicepl.ArticleServicepl;

import java.util.List;

public interface ArticleService {
    String registerArticle(Article article) throws Exception;

    List<Article> findAll();

    List<Article> findByArticleCtg(String number);
}
