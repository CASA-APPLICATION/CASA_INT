package kr.co.casa_int.controller;


import io.swagger.annotations.ApiOperation;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.LikeArticle;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.repository.LikeArticleRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.ArticleService;
import kr.co.casa_int.service.UserMgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping(value = "/article")

@RequiredArgsConstructor
@Slf4j

/**
 * @author gyutae park
 * @since 2023.08.04
 */

public class ArticleController {

    // Repo 쪽 지우고 서비스로 대체하자.
    private final ArticleRepo articleRepo;
    private final LikeArticleRepo likeArticleRepo;
    private final ArticleService articleService;

    //private final EntityManager entityManager;

    @GetMapping("/get/user/article/{where}/{number}")
    public ResponseEntity<Object> getArticle(@PathVariable(name ="where") String specificArticle,@RequestParam(name = "page", defaultValue = "0") int page
                                            ) throws  Exception {

        /**
         * 작품조회의 3가지 종류
         * 1. 회원, 비회원 등 전체목록 조히
         * 2. 회원, 비회원 등 type 에 따른 조회    : 조건 type
         * 3. 특정 작가의 작품 조회              : 조건 특정 작가
         *
         */

        try {
            // 전체 검색일 경우
            if (Objects.equals(specificArticle, "all")){
//                List<Article> articles = articleService.findAll();
                Page<Article> articlesPage = articleService.findAllPaginated(PageRequest.of(page, 10));
                return new ResponseEntity<>(articlesPage, HttpStatus.OK);
            }
            // 타입 검색일 경우
            else{
//                List<Article> articles = articleService.findByArticleCtg(number);
                Page<Article> articlesPage = articleService.findByArticleCtgPaginated(specificArticle, PageRequest.of(page, 10));
                return new ResponseEntity<>(articlesPage, HttpStatus.OK);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @ApiOperation(
            value = "작품 등록"
            , notes = "현재, 회원과 관련된 api 와 테이블이 정확히 정의가 안 되어 있어서, 하드코딩으로 하나의 유저가 있다고 가정하여 진행됨")
    @PostMapping("/post/member/article")
    public ResponseEntity<Article> registerArticle(@RequestBody Article article ,Principal principal) throws  Exception{

        try{
            // 회원 검색
//            User user = userMgRepo.findById(principal.getName());
//            int userSeq = user.getUserSequenceId();

            // 작품 등록
            String result = articleService.registerArticle(article);
            log.debug(result);
            // 200OK
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            // 내부 회원 아이디가 없거나, 다른 오류 상의 내부 에러가 발생하였을 때.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
