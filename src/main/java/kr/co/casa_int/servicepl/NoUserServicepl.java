package kr.co.casa_int.servicepl;

import kr.co.casa_int.security.SecurityConfig;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.service.NoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoUserServicepl implements NoUserService {

    private final NoUserMgRepo noUserMgRepo;
    //private final TestNoUserMgRepo noUserMgRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final SecurityConfig securityConfig;

    public HashMap<String, String> registerNewMember(User userInfo) throws Exception {

        log.info("userInfo=[{}]",userInfo);

        HashMap<String, String> serverResponse = new HashMap<>();

        UserDto userDto = new UserDto();
        User user = new User();

        // entity to dto
        userDto = modelMapper.map(user, UserDto.class);
        // 여기서 비밀번호를 인코딩해서 저장.
        //userDto.setUpw(passwordEncoder.encode(userInfo.getUpw()));


        userInfo.setUpw(passwordEncoder.encode(userInfo.getUpw()));


        log.info("userDto=[{}]",userInfo);

        try{
            noUserMgRepo.save(userInfo);
            log.info("user info :[{}]",userInfo);
//            serverResponse.put("resultCode", "200OK");
//            serverResponse.put("user", userInfo.toString());
            // 회원 아이디 중복 확인

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

    // 로그인
    public UserDto login(UserDto userDto) throws  Exception{

        UserDto newUser = null ;

        return newUser;

    }

}
