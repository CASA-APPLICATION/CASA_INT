package kr.co.casa_int.servicepl;

import kr.co.casa_int.config.SecurityConfig;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.service.NoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoUserServicepl implements NoUserService {

    private final NoUserMgRepo noUserMgRepo;
    private final ModelMapper modelMapper;
    private final SecurityConfig securityConfig;

    public HashMap<String, String> registerNewMember(User userInfo) throws Exception {

        HashMap<String, String> serverResponse = new HashMap<>();

        UserDto userDto = new UserDto();
        User user = new User();

        // entity to dto
        userDto = modelMapper.map(user, UserDto.class);
        // 여기서 비밀번호를 인코딩해서 저장.
        userDto.setRoles("ROLE_USER");
        try{
            noUserMgRepo.save(userInfo);
            log.info("user info :[{}]",userInfo);
            serverResponse.put("resultCode", "200OK");
            serverResponse.put("user", userInfo.toString());
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
            log.info("user=[{}]", user);
            serverResponse.put("resultCode", "500ERROR");
            serverResponse.put("errorCode", e.toString());
            log.info(e.toString());
            return serverResponse;

        }
    }

}
