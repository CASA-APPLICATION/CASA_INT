package kr.co.casa_int.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 프로젝트 테스트를 위한 기본 apid
    @GetMapping( value = {"/api"})
    public String testController() {
        return "Hello Casa";
    }

}
