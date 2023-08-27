//package kr.co.casa_int.controller;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityManager;
//import kr.co.casa_int.entity.Article;
//import kr.co.casa_int.entity.User;
//import kr.co.casa_int.repository.ArticleRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//// 임시로 전체 허용
//@CrossOrigin("*")
//@RequestMapping(value = "/user")
//
//@RequiredArgsConstructor
//@Slf4j
//
///**
// * @author gyutae park
// * @since 2023.08.04
// */
//
//public class ArticleController {
//
//    private final ArticleRepo articleRepo;
//    private final EntityManager entityManager;
//
//    @GetMapping("/get/article")
//    public ResponseEntity<ArticleController> getArticle(Principal principal) throws  Exception {
//
//        String userId = principal.getName().toString();
//        List<Article> articleList = new ArrayList<Article>();
//
//
//        Article articles = entityManager.find(Article.class, principal.getName());
//
//
//        try {
//
//        }catch (Exception e){
//
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//}
