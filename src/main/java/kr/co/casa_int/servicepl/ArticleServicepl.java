package kr.co.casa_int.servicepl;

import kr.co.casa_int.entity.Article;
import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArticleServicepl implements ArticleService {

    private final ArticleRepo articleRepo;

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

}
