package kr.co.casa_int.service;

import kr.co.casa_int.dto.updateUserInfo;
import kr.co.casa_int.entity.User;

import java.security.Principal;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

public interface UserMgService {

    // 회원가입
    public String singUp(User userInfo) throws  Exception;
    // 유저 인증키, 문자넘버 확인
    //public boolean checkUserAuth(String uuid, String authKey) throws  Exception;
    // 회원정보 수정
    //public String updateUser(updateUserInfo userInfo, User loginUser) throws Exception;

}
