package kr.co.casa_int.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class noUserController {

    private final NoUserService noUserService;

    @ApiOperation(value ="회원가입", notes = "최초 회원가입용 api")
    @PutMapping(value = {"/registerNewMember"})
    public ResponseEntity<HashMap<String, String >> registerNewMember(@RequestBody User userInfo) {

        HashMap<String, String> response = new HashMap<>();
        log.info(userInfo);
        try {

            response = noUserService.registerNewMember(userInfo);
            log.info(response);
            return ResponseEntity.ok(response);

        }catch (Exception e){

        }

        return new ResponseEntity<>(null);
    }

}
