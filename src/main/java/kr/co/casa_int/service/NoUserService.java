package kr.co.casa_int.service;

import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Objects;

public interface NoUserService {

    public ResponseEntity<String> registerNewMember(User userInfo) throws Exception;

    UserDto login(UserDto userDto) throws Exception;
}
