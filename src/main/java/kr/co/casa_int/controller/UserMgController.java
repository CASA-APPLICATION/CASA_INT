package kr.co.casa_int.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@RestController
@RequestMapping( value = "/user")
// 임시로 다 뚫어둠
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserMgController {

    @GetMapping(value ={"/test"})
    public String userTestApi(){
        return "Hello /user/test Casa! ";
    }



}
