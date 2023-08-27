package kr.co.casa_int.controller;

import kr.co.casa_int.dto.updateUserInfo;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.service.UserMgService;
import kr.co.casa_int.utils.PhoneNumberChk;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @author gyutae park
 * @since 2023.04.10
 * @apiNote /user/no/** : 비회원 && /user/** : 회원
 */

@RestController
@RequestMapping( value = "/user")
// 임시로 다 뚫어둠
@CrossOrigin("*")

@RequiredArgsConstructor
public class UserMgController {

    private final UserMgService service;
    //private final PhoneNumberChk phoneNumberChk;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping(value = {"/test"})
    public String userTestApi() {
        return "Hello /user/test Casa! ";
    }

    // 아이디 생성 API
    // API_USER_001
    @PostMapping(value = {"/no/singUp"})
    public ResponseEntity<String> singUp(@RequestBody User userInfo) throws Exception {

        logger.info("RequestBody=[{}]\n", userInfo);

        String response = service.singUp(userInfo);

        logger.info("response=[{}]\n", response);

        // 무언가가 중복될 경우 : 닉네임, 이메일
        if (response.contains("duplicate")) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 아이디 생성 성공
        else if (response.contains("Success")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        // 무언가의 이유로 실패
        else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
        logger.info("loginUser=[{}]\n", loginUser.getNickname());

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
