package kr.co.casa_int.controller;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.LikeArticle;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.repository.LikeArticleRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserMgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
// 임시로 전체 허용
@CrossOrigin("*")
@RequestMapping(value = "article")

@RequiredArgsConstructor
@Slf4j

/**
 * @author gyutae park
 * @since 2023.08.04
 */

public class ArticleController {

    private final UserMgRepo userMgRepo;
    private final ArticleRepo articleRepo;
    private final LikeArticleRepo likeArticleRepo;
    //private final EntityManager entityManager;

    @GetMapping("/get/user/{where}")
    public ResponseEntity<Object> getArticle(@PathVariable(name ="where", required = false) String specificArticle, Principal principal, @RequestBody Article article) throws  Exception {

        /**
         * 작품조회의 3가지 종류
         * 1. 회원, 비회원 등 전체목록 조히
         * 2. 회원, 비회원 등 type 에 따른 조회    : 조건 type
         * 3. 특정 작가의 작품 조회              : 조건 특정 작가
         *
         */

        try {
            // 전체 검색일 경우
//            if ( article.getArticleCtg() == "all"){
//                List<Article> articles = articleRepo.findAll();
//                return new ResponseEntity<>(articles, HttpStatus.OK);
//            }
//            // 타입 검색일 경우
//            else if ( article.getArticleCtg() != "all" && article.getUserId() == 0 ){
//                List<Article> articles = articleRepo.findByArticleCtg(article.getArticleCtg());
//                return new ResponseEntity<>(articles, HttpStatus.OK);
//            }
//            // 특정 작가, 특정 타입 검색일 경우
//            else if ( article.getArticleCtg() != "all" && article.getUserId() != 0){
//                return null;
//            }
            // 특정 작가 검색일 경우
            //return new ResponseEntity<>(specificArticle,HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/post/member/article")
    public ResponseEntity<Article> registerArticle(Principal principal) throws  Exception{


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
