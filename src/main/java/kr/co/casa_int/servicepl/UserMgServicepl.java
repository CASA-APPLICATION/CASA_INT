package kr.co.casa_int.servicepl;

import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserMgService;
import kr.co.casa_int.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Service
@Log4j2
@RequiredArgsConstructor
public class UserMgServicepl implements UserMgService {

    private final UserMgRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil ;

    private final NoUserMgRepo noUserMgRepo;
    private final UserMgRepo userMgRepo;

    /**
     * @apiNote 회원탈퇴
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<String> leaveMember(Long id) {
        try {
            Optional<User> user = userMgRepo.findById(id);
            user.ifPresent(value -> value.setLeaveUser("true"));
            return new ResponseEntity<>(user.toString(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 사용자 문자 인증
    // 1. redis 인증키 확인
    // 2. 문자 인증넘버 확인
    //public boolean checkUserAuth(String uuid, String authKey) throws  Exception{

        // 1.
//        String userAuthKey = redisUtil.getData(uuid);
//        if ( userAuthKey == null ){
//            throw new Exception("유효하지 않은 인증키입니다.");
//        }
//        // 2.
//        else {
//            if ( userAuthKey.equals(authKey) ){
//                // redis 에 키를 사용했으니 삭제한다.
//                redisUtil.deleteData(uuid);
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
    //}

    // 회원정보 수정
    // 비밀번호와 같은 경우는, 사용자가 입력한 비밀번호를 암호화하여 DB에 저장된 값과 비교하여 매칭한다.
    //@Transactional
    //public String updateUser(updateUserInfo userInfo, User loginUser) throws  Exception{

//
//
//        ModelMapper modelMapper = new ModelMapper();
//        String userEncoderPwd = passwordEncoder.encode(userInfo.getNowPwd());
//
//        UserDto upadteUserInfo = new UserDto();
//        upadteUserInfo = modelMapper.map(loginUser, UserDto.class);
//
//        // 만약 현재 비밀번호가 일치한다면. || 비밀번호 재확인도 알맞게 입력하였다면.
//        if ( loginUser.getPassword().equals(userEncoderPwd) && userInfo.getUpdatePwd().equals(userInfo.getCheckPwd())) {
//            upadteUserInfo.setPassword(userInfo.getUpdatePwd());
//            return "updateUser Success";
//        }
//        // 만약 현재 비밀번호가 일치하지 않거나, 비밀번호 재확인이 일치하지 않는다면.
//        else {
//            return "updateUser Fail";
//        }
//
//    }

}
