package kr.co.casa_int.servicepl;

import jakarta.persistence.EntityManager;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServicepl implements ArticleService {

    private final ArticleRepo articleRepo;

    private final EntityManager em;


    public String registerArticle(Article article) throws  Exception{
        log.info("article=[{}]",article.toString());
        try {
            articleRepo.save(article);
        }catch (Exception e){
            log.info("error=[{}]", e.toString());
            return e.toString();
        }

        return "register success";
    }

    @Override
    public List<Article> findAll() {

        return articleRepo.findAll();
    }

    @Override
    public List<Article> findByArticleCtg(String number) {
        return articleRepo.findByArticleCtg(number);
    }

    @Override
    public Page<Article> findAllPaginated(Pageable pageable) {
        return articleRepo.findAll(pageable);
    }

    @Override
    public Page<Article> findByArticleCtgPaginated(String category, Pageable pageable) {
        return articleRepo.findByArticleCtg(category, pageable);
    }


//    @Override
//    public List<Article> findByArticleCtg(String number){
//        TypedQuery<Article> findQuery = em.createQuery("select a from Article as a where a.articleCtg = :number", Article.class);
//        List<Article> result = findQuery.getResultList();
//
//
//        return result;
//    }

//    @Override public Optional findByName(String name) {
//        List result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name) .getResultList();
//        return result.stream().findAny();
//    }

}
