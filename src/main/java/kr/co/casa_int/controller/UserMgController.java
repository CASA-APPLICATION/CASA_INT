package kr.co.casa_int.controller;

import kr.co.casa_int.dto.updateUserInfo;
import kr.co.casa_int.entity.Article;
import kr.co.casa_int.entity.LikeArticle;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.LikeArticleRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserMgService;
import kr.co.casa_int.utils.PhoneNumberChk;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.*;

/**
 * @author gyutae park
 * @since 2023.04.10
 * @apiNote /user/no/** : 비회원 && /user/** : 회원
 */

@RestController
@RequestMapping( value = "/user")
// 임시로 다 뚫어둠
@CrossOrigin("*")
@Log4j2
@RequiredArgsConstructor
public class UserMgController {

    // 여기서 서버 저장 경로를 적어주자.

    private final UserMgService service;
    private final UserMgRepo userMgRepo;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final LikeArticleRepo likeArticleRepo;

    // 장바구니 보기
    @GetMapping(value = {"/showBasket"})
    public ResponseEntity<List<Article>> showBasket(Principal principal) throws Exception{
        return service.showBasket(principal);
    }
    // 장바구니 담기
    @PutMapping(value = {"/addBasket"})
    public ResponseEntity<String> addBasket(Article article, Principal principal) throws Exception{
        return service.addBasket(article, principal);
    }

    // 장바구니 삭제
    @DeleteMapping(value = {"/deleteBasket"})
    public ResponseEntity<String> deleteBasket(Article article ,Principal principal) throws Exception{
        return service.deleteBasket(article, principal);
    }

    // 프로필 수정
    @PostMapping(value = {"/modifyProfile"})
    public ResponseEntity<String> modifyProfile(User user, Principal principal) throws Exception{
        // 수정하고자 하는 데이터가 무엇인지.?
        // 이메일, 비밀번호, 닉네임, 휴대폰 번호, 역할(구매자 & 판매자), 이미지, 등등...

        

        return null;
    }

    // 20240226 북마크 기능
    // 20240311 북마크 -> 좋아요로 변경
    @PostMapping(value = {"/likeArticle"})
    public ResponseEntity<String> likeArticle(LikeArticle likeArticle, Principal principal) throws  Exception {

        String response = service.likeArticle(likeArticle, principal).toString();

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 20240308 북마크 해제 기능
    // 20240311 북마크 -> 좋아요로 변경
    @DeleteMapping(value = {"/unLikeArticle"})
    public ResponseEntity<String> unBookMark(LikeArticle likeArticle, Principal principal) throws Exception{

        String response = service.unLikeArticle(likeArticle, principal).toString();

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원 탈퇴
    @PostMapping(value = {"/leaveMember"})
    public ResponseEntity<String> leaveMember(Principal principal) throws Exception {

        String userUid = "";

        // 로그인한 사용자 정보 가져오기.
        try {
            // 로그인했을 때.
            userUid = principal.getName();
            log.info("leaveMember userUid=[{}]", userUid);
        }catch (Exception e){
            // 로그인하지 않았을 경우 종료
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
        User userInfo = userMgRepo.findByUid(userUid);
        Long userId = userInfo.getId();
        log.info("leaveMember userInfo=[{}]", userInfo.toString());

        // 회원탈퇴 프로세스
        try {
            log.info("leaveMember success");
            return new ResponseEntity<>(service.leaveMember(userId).toString(), HttpStatus.OK);
        }catch (Exception e){
            log.info("leaveMember error=[{}]", service.leaveMember(userId).toString());
            return new ResponseEntity<>(service.leaveMember(userId).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // 밑에서부터 test 코드

    @GetMapping(value = {"/test/principal"})
    public String principalTest(Principal principal) throws Exception {

        log.info("principal test=[{}]",principal.getName());

        return principal.getName();

    }

    @GetMapping(value = {"justLoginPage"})
    public String justLoginPage() throws  Exception {
        return "Okay, if u can look the sentence, ur session connect is success.\n" +
                "this is just login redirect page.\n" +
                "so if u inform login redirect front page, i will chagne ur front page.\n" +
                "now, The reason I use English is because Korean is broken.\n" +
                "and then, lets develop CASA  !!";
    }

    @GetMapping(value = {"/test"})
    public String userTestApi() {
        return "Hello /user/test Casa! ";
    }


    // 아이디 생성 API
    // API_USER_001



    // 문자전송 api
    // API_USER_002
//    @PostMapping(value = {"/sendMessage/{phoneNumber}"})
//    public ResponseEntity<Object> sendMessageAuthKey(@PathVariable String phoneNumber) throws  Exception{
//
//        HashMap<String, Object> result = phoneNumberChk.sendMessage(phoneNumber);
//
//        if ( result.size() != 0 ){
//            logger.info("response=[{}]\n", result);
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // 문자 인증 api
    // API_USER_003
    @GetMapping(value = {"/chkAuthUserPhone/{phoneNumber}/{authKey}"})
    public ResponseEntity<Object> chkPhoneUserPhone(@PathVariable String phoneNumber, @PathVariable String authKey) throws Exception{

        //boolean response = service.checkUserAuth(phoneNumber, authKey);
        boolean response = false;

        // redis 에 인증키가 있을 경우
        if ( response == true ){
            logger.info("response=[{}]\n", response);
            // resutn true
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            logger.info("response=[{}]\n", response);
            // return false
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

    }


    // API_USER_004
    // 회원정보 수정완료 버튼 api
    // 여기서 핸드폰 인증을 수행했다면 예시로 프론트에서 인증번호란을 display: none 등과 같이 해준 후,
    // 해당 input 부분에 none 클래스가 있다면 해당 api 를 부를 수 있게 하는 방법이 있지 않을까?
    // https://www.notion.so/fb5e31d3b607424b87b6b99e0a54c27d
    @PutMapping(value = {"/updateUser"})
    public ResponseEntity<String> updateUser(@RequestBody updateUserInfo userInfo, @AuthenticationPrincipal User loginUser) throws Exception{
        // 참고
        // https://u-it.tistory.com/entry/BCrypt-Spring-security-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EC%95%94%ED%98%B8%ED%99%94-%EB%B3%B5%ED%98%B8%ED%99%94-%EB%A1%9C%EC%A7%81-%ED%99%9C%EC%9A%A9

        logger.info("RequestBody=[{}]\n", userInfo);
        //logger.info("loginUser=[{}]\n", loginUser.getNickname());

        // 회원정보 수정
        //String response = service.updateUser(userInfo, loginUser);
        String response = "False";

        logger.info("response=[{}]\n", response);

        if( response.contains("Success") ){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
