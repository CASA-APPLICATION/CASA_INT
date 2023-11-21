package kr.co.casa_int.servicepl;

import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.service.NoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoUserServicepl implements NoUserService {

    private final NoUserMgRepo noUserMgRepo;

    public HashMap<String, String> registerNewMember(User userInfo) throws Exception {

        HashMap<String, String> serverResponse = new HashMap<>();

        try{
            noUserMgRepo.save(userInfo);
            // 회원 아이디 중복 확인
//            if ( noUserMgRepo.findById(userInfo.getId()).equals(userInfo.getId()) ){
//                // 에러 구문 따로 빼야할듯.
//                serverResponse.put("status", "500error");
//                serverResponse.put("errorCode", "아이디 중복");
//                return serverResponse;
//            }

            // 회원 이메일 중복 확인, 회원 휴대폰 번호 중복 확인


            // 회원 비밀번호 적합여부 확인

            //

            return serverResponse;

        }catch (Exception e){

            return serverResponse;

        }
    }

}
