package kr.co.casa_int.controller;


//import kr.co.casa_int.entity.Article;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.User;
//import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.repository.ArticleRepo;
import kr.co.casa_int.repository.UserMgRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
@CrossOrigin("*")
@RequestMapping(value = "/test")
// bean 관리는 @autowired 가 아닌 아래의 어노테이션을 활용한다
@RequiredArgsConstructor
public class sampleController {

    private final ArticleRepo articleRepo;
    private final UserMgRepo userMgRepo;

    @GetMapping(value = "/getUserInfo")
    public String getUserInfoTest(@AuthenticationPrincipal User user) {
        return user.toString();
    }

    @PostMapping("/logoutApi")
    public void logoutv2(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

    // 프로젝트 테스트를 위한 기본 apid
    @ApiOperation(value ="basicTestApi", notes = "단순 테스트 api")
    @GetMapping( value = {"/api"})
    public ResponseEntity<String> testController() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // article 관련

    @GetMapping(value = {"/get/user/article"})
    public ResponseEntity<List<Article>> getArticle() {
        List<Article> articles = new ArrayList<Article>();
        articles = articleRepo.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK) ;
    }
    @PostMapping(value = {"/post/member/article"})
    public ResponseEntity<Article> postArticle(@RequestBody Article article){

        articleRepo.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);

    }

}
