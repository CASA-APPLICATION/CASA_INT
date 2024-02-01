package kr.co.casa_int.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.security.CustomUserDetailService;
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
    private final CustomUserDetailService customUserDetailService;

    /**
     * @param userInfo
     * @return
     * @throws Exception
     */
    @ApiOperation(value ="회원가입", notes = "최초 회원가입용 api")
    @PutMapping(value = {"/registerNewMember"})
    public ResponseEntity<Object> registerNewMember(@RequestBody User userInfo) throws Exception {
        Object response = noUserService.registerNewMember(userInfo);
        try {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author park
     * @since 2023.01.12
     */
    @ApiOperation(value ="로그인1", notes = "로그인 api")
    @PostMapping(value = {"/login/{uid}"})
    public String login(@PathVariable String uid) throws  Exception{

        log.info("uid=[{}]",uid);

        customUserDetailService.loadUserByUsername(uid);

        return "login Success";
    }

}
