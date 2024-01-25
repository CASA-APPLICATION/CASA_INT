package kr.co.casa_int.security;

import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.UserMgRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;



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




        log.info("return key = [{}]", user.getUid() + user.getUpw() + user.getRole());

        log.info("org.springframework.security.core.userdetails.User.builder()\n" +
                "                .username(user.getUid())\n" +
                "                .password(user.getUpw())\n" +
                "                .roles(user.getRole())=\n[{}]",org.springframework.security.core.userdetails.User.builder()
                .username(user.getUid())
                .password(user.getUpw())
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .roles(user.getRole())
                //.roles(user.getRoles().stream().map(UserRole::getAuthority).toArray(String[]::new))

                .build());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUid())
                .password(user.getUpw())
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .roles(user.getRole())
                //.roles(user.getRoles().stream().map(UserRole::getAuthority).toArray(String[]::new))

                .build();

//        return new org.springframework.security.core.userdetails.User(
//                user.getUid(),
//                user.getUpw(),
//                //user.getRoles().stream().map(Enum::name).toArray(String[]::new)
//                //List.of(new SimpleGrantedAuthority(user.getRole()))
//
//                // 20240124
//                user.getAuthorities()
//
//                //getAuthorities(user)
//        );
    }

//    private static Collection<? extends GrantedAuthority> getAuthorities(User user){
//        String[] userRoles = user.getRoles()
//                .stream()
//                .map((role) -> role.getRoleName())
//                .toArray(String[]::new);
//
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }

}
