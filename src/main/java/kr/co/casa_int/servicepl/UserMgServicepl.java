package kr.co.casa_int.servicepl;

import kr.co.casa_int.config.SecurityConfig;
import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserMgService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Service
@RequiredArgsConstructor
public class UserMgServicepl implements UserMgService {

    private final UserMgRepo repository;
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    public String singUp(User userInfo) throws  Exception{

        ModelMapper modelMapper = new ModelMapper();

        List<User> users = new ArrayList<User>();

        for ( int i = 0 ; i < users.size() ; i ++ ){
            // 대문자로 변환 후 비교
            // 이메일 중복
            if ( !users.get(i).getUserEmail().toUpperCase().equals(userInfo.getUserEmail().toUpperCase()) ){
                return "Email duplicate";
            }
            // 닉네임 중복
            else if ( !users.get(i).getUserNickname().toUpperCase().equals(userInfo.getUserNickname().toUpperCase()) ){
                return "Nickname duplicate";
            }
            // 회원가입 성공
            else {
                // 비밀번호 암호화
                String encoderPassword = passwordEncoder.encode(userInfo.getPassword());
                // 비밀번호를 등록하기 위해 Dto 로 전환
                UserDto newUser = modelMapper.map(userInfo, UserDto.class);
                // 비밀번호 변경
                newUser.setPassword(encoderPassword);
                // 다시 entity 로 변경
                User newUserEntity = modelMapper.map(newUser, User.class);
                // 회원가입 완료
                repository.save(newUserEntity);
                return "singUp Success";
            }
        }
        // 유저 회원이 0명일 경우
        return "singUp Success";
    }

    // 회원정보 수정
    @Transactional
    public String updateUser(User userInfo, User loginUser) throws  Exception{





        return null;
    }

}
