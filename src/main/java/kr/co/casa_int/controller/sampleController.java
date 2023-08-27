package kr.co.casa_int.controller;


//import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.User;
//import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.repository.UserMgRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// 아래와 같이 맡은 부분의 이름과 날짜를 적는다.
/**
 * @author gyutae park
 * @since 2023.04.10å
 */

// 어노테이션은 희망하게 적는다.
@RestController
@RequestMapping(value = "/test")
// bean 관리는 @autowired 가 아닌 아래의 어노테이션을 활용한다
@RequiredArgsConstructor
public class sampleController {

//    private final ArticleRepo articleRepo;
    private final UserMgRepo userMgRepo;

    // 프로젝트 테스트를 위한 기본 apid
    @GetMapping( value = {"/api"})
    public String testController() {
        return "Hello Casa";
    }

    // article 관련
//    @GetMapping(value = {"/get/article"})
//    public ResponseEntity<List<Article>> getArticle() {
//        List<Article> articles = new ArrayList<Article>();
//        articles = articleRepo.findAll();
//        return new ResponseEntity<>(articles, HttpStatus.OK) ;
//    }
//    @PostMapping(value = {"/post/article"})
//    public ResponseEntity<Article> postArticle(@RequestBody Article article){
//
//        articleRepo.save(article);
//        return new ResponseEntity<>(article, HttpStatus.OK);
//
//    }

    //User 관련
    @GetMapping(value = {"test/get/user"})
    public ResponseEntity<List<User>> getUser(){
        List<User> users = new ArrayList<User>();
        users = userMgRepo.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
