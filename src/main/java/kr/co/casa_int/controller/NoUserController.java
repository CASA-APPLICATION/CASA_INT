package kr.co.casa_int.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.service.NoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
// tmp all *
@CrossOrigin("*")
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/noUser")
public class NoUserController {

    private final NoUserService noUserService;


    @ApiOperation(value ="회원가입", notes = "최초 회원가입용 api")
    @PutMapping(value = {"/registerNewMember"})
    public ResponseEntity<HashMap<String, String >> registerNewMember(@RequestBody User userInfo) throws Exception {
    // 20230112 park
    // RequestBody 에러로 인한 @ModelAttribute 대체
    //public String registerNewMember(@ModelAttribute User userInfo) throws Exception{

        HashMap<String, String> response = new HashMap<>();
        log.info("userInfo=[{}]", userInfo);
        response = noUserService.registerNewMember(userInfo);

        // 20230112 park
        // 이 부분은 회원가입 api 이니, return 값은 front 한테 로그인 url 을 받아서 넣어줘야할거 같다.
        return new ResponseEntity<>(response,HttpStatus.OK);
        //return "로그인 url 보여주기";
    }

    /**
     * @author park
     * @since 2023.01.12
     */
    @ApiOperation(value ="로그인1", notes = "로그인 api")
    @PostMapping(value = {"/login"})
    public String login(@ModelAttribute UserDto userDto) throws  Exception{

        UserDto loginResult = noUserService.login(userDto);

        if(loginResult != null){
            // 로그인 성공
            return "";
        }
        else {
            // 로그인 실패
            return "로그인 url";
        }

    }


}
