package kr.co.casa_int.repository;

import kr.co.casa_int.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    List<Article> findByArticleCtg(String ctg);
    Page<Article> findAll(Pageable pageable);
    Page<Article> findByArticleCtg(String category, Pageable pageable);
    List<Article> findByCreatedAtBetween(Date startOfMonth, Date endOfMonth);



}
