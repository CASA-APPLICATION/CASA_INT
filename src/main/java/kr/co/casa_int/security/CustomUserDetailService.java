package kr.co.casa_int.security;

import kr.co.casa_int.dto.UserDto;
import kr.co.casa_int.repository.NoUserMgRepo;
import kr.co.casa_int.repository.UserMgRepo;
import kr.co.casa_int.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService{//} implements UserDetailService {


    private final UserMgRepo userMgRepo;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws  Exception{

        //UserDto userById = username.findByloginId(username);



    //}

}
