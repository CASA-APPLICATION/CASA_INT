package kr.co.casa_int.servicepl;

import kr.co.casa_int.dto.SecurityMember;
import kr.co.casa_int.entity.User;
import kr.co.casa_int.repository.UserMgRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServicepl implements UserDetailsService {

    private final UserMgRepo userMgRepo;

//    public UserDetailsServicepl(UserMgRepo userMgRepo){
//        this.userMgRepo = userMgRepo;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {

        return Optional.ofNullable(userMgRepo.findByUemail(username))
                        .filter(m -> m!= null)
                        .map(m -> new SecurityMember(m)).get();

    }

//    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String[] userRoles = user.getRoles()
//                .stream()
//                .map((role) -> role.getRolename())
//                .toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }

}
