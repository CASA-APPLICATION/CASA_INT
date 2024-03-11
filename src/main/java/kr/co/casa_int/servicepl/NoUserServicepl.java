package kr.co.casa_int.servicepl;

import kr.co.casa_int.entity.Article;
import kr.co.casa_int.security.SecurityConfig;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.service.NoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoUserServicepl implements NoUserService {

    private final NoUserMgRepo noUserMgRepo;

    //private final TestNoUserMgRepo noUserMgRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    // 회원가입
    @Transactional
    public ResponseEntity<String> registerNewMember(User userInfo) throws Exception {

        log.info("userInfo=[{}]",userInfo);

        UserDto userDto = new UserDto();
        User user = new User();

        // entity to dto
        userDto = modelMapper.map(user, UserDto.class);
        // 여기서 비밀번호를 인코딩해서 저장.
        userInfo.setUpw(passwordEncoder.encode(userInfo.getUpw()));
        userInfo.setLeaveUser("False");
        // 사용자 이름 암호화 저장.
        userInfo.setUserName(passwordEncoder.encode(userInfo.getUserName()));
        // 사용자 주민번호 암호화 저장.
        userInfo.setUserResidentRegistrationNumber(passwordEncoder.encode(user.getUserResidentRegistrationNumber()));


        log.info("userDto=[{}]",userInfo);
        // 회원 아이디 중복 확인
        if ( noUserMgRepo.findByUid(userInfo.getUid()) != null ) {
            return new ResponseEntity<>("uid is already taken", HttpStatus.CONFLICT);
        }
        // 회원 이메일 중복 확인, 회원 휴대폰 번호 중복 확인
        if ( noUserMgRepo.findByUseEmail(userInfo.getUseEmail()) != null ) {
            return new ResponseEntity<>("user email is already taken", HttpStatus.CONFLICT);
        }
        // 회원 비밀번호 적합여부 확인

        try{
            noUserMgRepo.save(userInfo);
            log.info("user info :[{}]",userInfo);
            return new ResponseEntity<>("registerNewMember is Success", HttpStatus.OK);
        }catch (Exception e){
            log.info("user=[{}]", user);
            log.info(e.toString());
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인
    public UserDto login(UserDto userDto) throws  Exception{

        UserDto newUser = null ;

        return newUser;

    }

}
