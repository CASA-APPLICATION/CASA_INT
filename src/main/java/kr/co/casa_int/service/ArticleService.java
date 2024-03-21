package kr.co.casa_int.service;

import kr.co.casa_int.entity.Article;
import kr.co.casa_int.servicepl.ArticleServicepl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    String registerArticle(Article article) throws Exception;

    List<Article> findAll();

    List<Article> findByArticleCtg(String number);


    Page<Article> findAllPaginated(Pageable of);


    Page<Article> findByArticleCtgPaginated(String category, Pageable pageable);
}