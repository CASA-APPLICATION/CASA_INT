package kr.co.casa_int.security;

import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailService implements UserDetailsService {

    private final UserMgRepo userMgRepo;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        User user = new User();
        user = userMgRepo.findByUid(uid);

        if( user == null ){
            log.info("없는 회원입니다.");
            throw new UsernameNotFoundException("User not found with uid=[{" + uid + "}]");
        }
        else {
            log.info("access id=[{}]", uid);
            log.info("회원입니다.");
            //return
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUid(),
                user.getUpw(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }

}
