package kr.co.casa_int.controller;

import kr.co.casa_int.service.UserMgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value ={"/test"})
    public String userTestApi(){
        return "Hello /user/test Casa! ";
    }

    //@PostMapping(value = {"/"})



}
