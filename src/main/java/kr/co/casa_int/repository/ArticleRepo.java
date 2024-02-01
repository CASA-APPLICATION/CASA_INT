package kr.co.casa_int.repository;

import kr.co.casa_int.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    public List<Article> findByArticleCtg(String ctg);
//    public Article findByUser_userId(String userId);
    //public List<Article> findByArticleCtgAndUserId(String ctg, int id);
}
